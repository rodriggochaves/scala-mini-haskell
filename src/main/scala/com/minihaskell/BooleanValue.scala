package com.minihaskell

case class BooleanValue(val value: Boolean) extends ConcreteValue[Boolean](value) {
  override def evalType(): Type = BooleanType()
}
