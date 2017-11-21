package com.minihaskell

import com.minihaskell.memory.RunningEnvironment

class LetExpression( val id : String, 
                     val namedExpression : Expression, 
                     val body : Expression ) extends Expression {
  
  override def eval(): Value = {
    val value = namedExpression.eval()
    RunningEnvironment.update( id, value )
    return body.eval()
  }

}