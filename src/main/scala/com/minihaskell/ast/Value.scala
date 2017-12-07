package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment

sealed trait Value extends Expression

sealed abstract class IrreducibleValue extends Value {
  def eval(): Value = this
}

case class BooleanValue(value: Boolean) extends IrreducibleValue
case class IntValue(value: Integer)     extends IrreducibleValue
case class Closure(id: String, body: Expression, env: Map[String, Value])
  extends IrreducibleValue

case class Function(name: String, args: Map[String, Unit], body: Expression)
  extends IrreducibleValue {

  override def eval() = {
    RunningEnvironment.update(name, this)
    this
  }
}
