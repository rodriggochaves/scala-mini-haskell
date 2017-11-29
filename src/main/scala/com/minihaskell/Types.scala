package com.minihaskell

trait Type

case class ErrorType() extends Type

case class IntegerType() extends Type

case class BooleanType() extends Type