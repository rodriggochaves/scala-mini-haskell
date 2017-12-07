package com.minihaskell.ast

import com.minihaskell.memory.{Gama, RunningEnvironment}

case class ReferenceExpression(variable: String) extends Expression {
  override def eval(): Value = RunningEnvironment.query(variable)
  override def evalType(): Type = Gama.find(variable)
}
