package com.minihaskell

import scala.collection.mutable

sealed trait Value extends Expression

sealed abstract class IrreducibleValue extends Value {
  def eval(): Value = this
}

case class BooleanValue(value: Boolean) extends IrreducibleValue
case class IntValue(value: Integer)     extends IrreducibleValue
case class Closure(id: String, body: Expression, env: mutable.HashMap[String, Value])
  extends IrreducibleValue
