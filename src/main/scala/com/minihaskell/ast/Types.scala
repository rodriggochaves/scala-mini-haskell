package com.minihaskell.ast

sealed trait Type

object     ErrorType                                extends Type
object     IntegerType                              extends Type
object     BooleanType                              extends Type
case class FnType(from: List[Type], to: List[Type]) extends Type
