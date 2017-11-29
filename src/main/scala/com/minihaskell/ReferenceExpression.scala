package com.minihaskell

import com.minihaskell.memory.RunningEnvironment
import com.minihaskell.memory.Gama
import com.minihaskell.exceptions.UndeclaredVariableException

case class ReferenceExpression( variable: String ) extends Expression {

  override def eval(): Value = {
    try {
      return RunningEnvironment.query(variable)
    } catch {
      case _: Throwable => throw UndeclaredVariableException() 
    }
    }

  override def evalType(): Type = {
    Gama.find( variable )
  }
 
}