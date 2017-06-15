{-|
Module : ADL.Compiler.Backends.Typescript
Description: Typescript backend for ADL

This module contains that necessary functions to generate
a typescript backend from an ADL file.
-}
{-# LANGUAGE OverloadedStrings #-}
module ADL.Compiler.Backends.Typescript(
 generate,
  TypescriptFlags(..),
  ) where

import           ADL.Compiler.AST
import           ADL.Compiler.Backends.Typescript.DataTypes
import           ADL.Utils.FileDiff                         (dirContents)
import qualified Data.ByteString.Lazy                       as LBS
import qualified Data.Map                                   as Map
import qualified Data.Text                                  as T
import qualified Data.Text.Encoding                         as T

import           ADL.Compiler.EIO
import           ADL.Compiler.Processing
import           ADL.Compiler.Utils
import           ADL.Utils.IndentedCode
import           Control.Monad                              (when)
import           Control.Monad.Trans                        (liftIO)
import           Control.Monad.Trans.State.Strict
import           Data.Foldable                              (for_)
import           Data.List                                  (intersperse)
import           Data.Monoid
import           Data.Traversable                           (for)
import           System.FilePath                            (joinPath,
                                                             takeDirectory,
                                                             (<.>), (</>))

import           ADL.Compiler.Backends.Typescript.Newtype   (genNewtype)
import           ADL.Compiler.Backends.Typescript.Struct    (genStruct)
import           ADL.Compiler.Backends.Typescript.Typedef   (genTypedef)
import           ADL.Compiler.Backends.Typescript.Union     (genUnion)
import           ADL.Compiler.Backends.Typescript.Common    (addImport,addAstMap)
import           ADL.Compiler.DataFiles

-- | Run this backend on a list of ADL modules. Check each module
-- for validity, and then generate the code for it.
generate :: AdlFlags -> TypescriptFlags -> FileWriter -> [FilePath] -> EIOT ()
generate af tf fileWriter modulePaths = catchAllExceptions  $ do
  for modulePaths $ \modulePath -> do
    m <- loadAndCheckModule af modulePath
    let m' = fullyScopedModule m
    generateModule tf fileWriter m'
  when (tsIncludeRuntime tf) (generateRuntime af tf fileWriter modulePaths)

-- JS.generate af (JS.JavascriptFlags {}) fileWriter
generateRuntime :: AdlFlags -> TypescriptFlags -> FileWriter -> [FilePath] -> EIOT ()
generateRuntime af tf fileWriter modulePaths = do
    files <- liftIO $ dirContents runtimeLibDir
    liftIO $ for_ files $ \inpath -> do
      content <- LBS.readFile (runtimeLibDir </> inpath)
      fileWriter (tsRuntimeDir tf </> inpath) content
    where
      runtimeLibDir = typescriptRuntimeDir (tsLibDir tf)

-- | Generate and the typescript code for a single ADL module, and
-- save the resulting code to the apppropriate file
generateModule :: TypescriptFlags ->
                  FileWriter ->
                  RModule ->
                  EIO T.Text ()
generateModule tf fileWriter m0 = do
  let moduleName = m_name m
      m = associateCustomTypes getCustomType moduleName m0
      cgp = CodeGenProfile {
        cgp_includeAst = not (tsExcludeAst tf)
      }
      mf = execState (genModule m) (emptyModuleFile (m_name m) cgp)
  liftIO $ fileWriter (moduleFilePath (unModuleName moduleName) <.> "ts") (genModuleCode mf)

genModule :: CModule -> CState ()
genModule m = do
  includeAst <- fmap (cgp_includeAst . mfCodeGenProfile) get
  when includeAst $ do
    addImport "ADL" (TSImport "ADL" ["runtime","adl"])

  -- Generate each declaration
  for_ (Map.elems (m_decls m)) $ \decl ->
    case d_type decl of
     (Decl_Struct struct)   -> genStruct m decl struct
     (Decl_Union union)     -> genUnion m decl union
     (Decl_Typedef typedef) -> genTypedef m decl typedef
     (Decl_Newtype ntype)   -> genNewtype m decl ntype

  when includeAst $ do
    addAstMap m

genModuleCode :: ModuleFile -> LBS.ByteString
genModuleCode mf = LBS.fromStrict (T.encodeUtf8 (T.unlines (codeText 10000 code)))
  where
    code
      =  cline "/* Automatically generated by adlc */"
      <> cline ""
      <> mconcat [genImport (mfModuleName mf) i | i <- Map.elems (mfImports mf)]
      <> cline ""
      <> mconcat (intersperse (cline "") (reverse (mfDeclarations mf)))

genImport :: ModuleName -> TSImport -> Code
genImport intoModule TSImport{iAsName=asName, iModulePath=importPath} = ctemplate "import * as $1 from \'$2\';" [asName, mpath]
  where
    mpath = T.intercalate "/" (".":relativeImport)

    intoPath = unModuleName intoModule
    relativeImport = relativePath (init intoPath) (init importPath) ++ [last importPath]

    relativePath [] ps2 = ps2
    relativePath (p1:ps1) (p2:ps2) | p1 == p2 = relativePath ps1 ps2
    relativePath ps1 ps2 = (map (const "..") ps1) <> ps2

emptyModuleFile :: ModuleName -> CodeGenProfile -> ModuleFile
emptyModuleFile mn cgp = ModuleFile mn Map.empty [] cgp

getCustomType :: ScopedName -> RDecl -> Maybe CustomType
getCustomType _ _ = Nothing

moduleFilePath  :: [Ident] -> FilePath
moduleFilePath path = joinPath (map T.unpack path)
