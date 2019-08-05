// @generated from adl module sys.adlast

use serde::Deserialize;
use crate::adl::sys::types::Map;
use crate::adl::sys::types::Maybe;
use serde::Serialize;

pub type ModuleName = String;

pub type Ident = String;

pub type Annotations = Map<ScopedName, serde_json::Value>;

#[derive(Serialize,Deserialize)]
pub struct ScopedName {
  #[serde(rename="moduleName")]
  pub module_name: ModuleName,

  pub name: Ident,
}

impl ScopedName {
  pub fn new(module_name: ModuleName, name: Ident) -> ScopedName {
    ScopedName {
      module_name: module_name,
      name: name,
    }
  }
}

#[derive(Serialize,Deserialize)]
pub enum TypeRef {
  #[serde(rename="primitive")]
  Primitive(Ident),

  #[serde(rename="typeParam")]
  TypeParam(Ident),

  #[serde(rename="reference")]
  Reference(ScopedName),
}

#[derive(Serialize,Deserialize)]
pub struct TypeExpr {
  #[serde(rename="typeRef")]
  pub type_ref: TypeRef,

  pub parameters: Vec<TypeExpr>,
}

impl TypeExpr {
  pub fn new(type_ref: TypeRef, parameters: Vec<TypeExpr>) -> TypeExpr {
    TypeExpr {
      type_ref: type_ref,
      parameters: parameters,
    }
  }
}

#[derive(Serialize,Deserialize)]
pub struct Field {
  pub name: Ident,

  #[serde(rename="serializedName")]
  pub serialized_name: Ident,

  #[serde(rename="typeExpr")]
  pub type_expr: TypeExpr,

  pub default: Maybe<serde_json::Value>,

  pub annotations: Annotations,
}

impl Field {
  pub fn new(name: Ident, serialized_name: Ident, type_expr: TypeExpr, default: Maybe<serde_json::Value>, annotations: Annotations) -> Field {
    Field {
      name: name,
      serialized_name: serialized_name,
      type_expr: type_expr,
      default: default,
      annotations: annotations,
    }
  }
}

#[derive(Serialize,Deserialize)]
pub struct Struct {
  #[serde(rename="typeParams")]
  pub type_params: Vec<Ident>,

  pub fields: Vec<Field>,
}

impl Struct {
  pub fn new(type_params: Vec<Ident>, fields: Vec<Field>) -> Struct {
    Struct {
      type_params: type_params,
      fields: fields,
    }
  }
}

#[derive(Serialize,Deserialize)]
pub struct Union {
  #[serde(rename="typeParams")]
  pub type_params: Vec<Ident>,

  pub fields: Vec<Field>,
}

impl Union {
  pub fn new(type_params: Vec<Ident>, fields: Vec<Field>) -> Union {
    Union {
      type_params: type_params,
      fields: fields,
    }
  }
}

#[derive(Serialize,Deserialize)]
pub struct TypeDef {
  #[serde(rename="typeParams")]
  pub type_params: Vec<Ident>,

  #[serde(rename="typeExpr")]
  pub type_expr: TypeExpr,
}

impl TypeDef {
  pub fn new(type_params: Vec<Ident>, type_expr: TypeExpr) -> TypeDef {
    TypeDef {
      type_params: type_params,
      type_expr: type_expr,
    }
  }
}

#[derive(Serialize,Deserialize)]
pub struct NewType {
  #[serde(rename="typeParams")]
  pub type_params: Vec<Ident>,

  #[serde(rename="typeExpr")]
  pub type_expr: TypeExpr,

  pub default: Maybe<serde_json::Value>,
}

impl NewType {
  pub fn new(type_params: Vec<Ident>, type_expr: TypeExpr, default: Maybe<serde_json::Value>) -> NewType {
    NewType {
      type_params: type_params,
      type_expr: type_expr,
      default: default,
    }
  }
}

#[derive(Serialize,Deserialize)]
pub enum DeclType {
  #[serde(rename="struct_")]
  Struct(Struct),

  #[serde(rename="union_")]
  Union(Union),

  #[serde(rename="type_")]
  Type(TypeDef),

  #[serde(rename="newtype_")]
  Newtype(NewType),
}

#[derive(Serialize,Deserialize)]
pub struct Decl {
  pub name: Ident,

  pub version: Maybe<u32>,

  #[serde(rename="type_")]
  pub r#type: DeclType,

  pub annotations: Annotations,
}

impl Decl {
  pub fn new(name: Ident, version: Maybe<u32>, r#type: DeclType, annotations: Annotations) -> Decl {
    Decl {
      name: name,
      version: version,
      r#type: r#type,
      annotations: annotations,
    }
  }
}

#[derive(Serialize,Deserialize)]
pub struct ScopedDecl {
  #[serde(rename="moduleName")]
  pub module_name: ModuleName,

  pub decl: Decl,
}

impl ScopedDecl {
  pub fn new(module_name: ModuleName, decl: Decl) -> ScopedDecl {
    ScopedDecl {
      module_name: module_name,
      decl: decl,
    }
  }
}

pub type DeclVersions = Vec<Decl>;

#[derive(Serialize,Deserialize)]
pub enum Import {
  #[serde(rename="moduleName")]
  ModuleName(ModuleName),

  #[serde(rename="scopedName")]
  ScopedName(ScopedName),
}

#[derive(Serialize,Deserialize)]
pub struct Module {
  pub name: ModuleName,

  pub imports: Vec<Import>,

  pub decls: std::collections::HashMap<String,Decl>,

  pub annotations: Annotations,
}

impl Module {
  pub fn new(name: ModuleName, imports: Vec<Import>, decls: std::collections::HashMap<String,Decl>, annotations: Annotations) -> Module {
    Module {
      name: name,
      imports: imports,
      decls: decls,
      annotations: annotations,
    }
  }
}