package com.minihaskell.ast

sealed trait Type

case object ErrorType                          extends Type
case object IntegerType                        extends Type
case object BooleanType                        extends Type
case class  FnType(from: List[Type], to: Type) extends Type
