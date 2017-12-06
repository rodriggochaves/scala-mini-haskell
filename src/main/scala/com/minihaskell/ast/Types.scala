package com.minihaskell.ast

sealed trait Type

case class ErrorType()   extends Type
case class IntegerType() extends Type
case class BooleanType() extends Type
case class ArrowType()   extends Type
