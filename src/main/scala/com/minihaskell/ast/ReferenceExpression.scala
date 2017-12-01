package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment
import com.minihaskell.exceptions.UndeclaredVariableException

case class ReferenceExpression(variable: String) extends Expression {

  override def eval(): Value = {
    RunningEnvironment.query(variable)
  }

}
