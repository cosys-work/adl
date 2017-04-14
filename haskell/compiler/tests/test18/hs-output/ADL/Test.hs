{-# LANGUAGE OverloadedStrings #-}
module ADL.Test(
    X1(..),
    X2(..),
    Y1(..),
    Y2(..),
) where

import ADL.Core
import Control.Applicative( (<$>), (<*>), (<|>) )
import qualified Data.Aeson as JS
import qualified Data.HashMap.Strict as HM
import qualified Data.Proxy
import qualified Data.Text as T
import qualified Prelude

data X1
    = X1_f1 Prelude.Double
    | X1_f2 Y1
    deriving (Prelude.Eq,Prelude.Ord,Prelude.Show)

instance AdlValue X1 where
    atype _ = "test.X1"
    
    jsonGen = genUnion (\jv -> case jv of
        X1_f1 v -> genUnionValue "f1" v
        X1_f2 v -> genUnionValue "f2" v
        )
    
    jsonParser
        =   parseUnionValue "f1" X1_f1
        <|> parseUnionValue "f2" X1_f2

data X2 = X2
    { x2_f1 :: Prelude.Double
    , x2_f2 :: [Y2]
    }
    deriving (Prelude.Eq,Prelude.Ord,Prelude.Show)

mkX2 :: Prelude.Double -> [Y2] -> X2
mkX2 f1 f2 = X2 f1 f2

instance AdlValue X2 where
    atype _ = "test.X2"
    
    jsonGen = genObject
        [ genField "f1" x2_f1
        , genField "f2" x2_f2
        ]
    
    jsonParser = X2
        <$> parseField "f1"
        <*> parseField "f2"

data Y1
    = Y1_f1 T.Text
    | Y1_f2 X1
    deriving (Prelude.Eq,Prelude.Ord,Prelude.Show)

instance AdlValue Y1 where
    atype _ = "test.Y1"
    
    jsonGen = genUnion (\jv -> case jv of
        Y1_f1 v -> genUnionValue "f1" v
        Y1_f2 v -> genUnionValue "f2" v
        )
    
    jsonParser
        =   parseUnionValue "f1" Y1_f1
        <|> parseUnionValue "f2" Y1_f2

data Y2 = Y2
    { y2_f1 :: T.Text
    , y2_f2 :: [X2]
    }
    deriving (Prelude.Eq,Prelude.Ord,Prelude.Show)

mkY2 :: T.Text -> [X2] -> Y2
mkY2 f1 f2 = Y2 f1 f2

instance AdlValue Y2 where
    atype _ = "test.Y2"
    
    jsonGen = genObject
        [ genField "f1" y2_f1
        , genField "f2" y2_f2
        ]
    
    jsonParser = Y2
        <$> parseField "f1"
        <*> parseField "f2"