package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment

sealed trait Value extends Expression

case class BooleanValue(value: Boolean) extends Value {
  override def eval(): Value = this
  override def evalType(): Type = BooleanType()
}

case class IntValue(value: Integer) extends Value {
  override def eval(): Value = this
  override def evalType(): Type = IntegerType()
}

case class Closure(id: String, _type: Type, body: Expression, env: Map[String, Value])
  extends Value {

  override def eval(): Value = this
  override def evalType(): Type = body.evalType()
}

case class Function(name: String, args: Map[String, Unit], body: Expression)
  extends Value {

  override def eval(): Value = {
    RunningEnvironment.update(name, this)
    this
  }

  override def evalType(): Type = ErrorType()
}
