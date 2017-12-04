package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment

case class ReferenceExpression(variable: String) extends Expression {
  override def eval(): Value = RunningEnvironment.query(variable)
}
