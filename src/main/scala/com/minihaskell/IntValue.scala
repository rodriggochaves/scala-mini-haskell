package com.minihaskell

case class IntValue( val value: Integer ) extends ConcreteValue[Integer](value) {
  override def evalType(): Type = IntegerType()
}