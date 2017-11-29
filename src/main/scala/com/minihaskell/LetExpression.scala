package com.minihaskell

import com.minihaskell.memory.RunningEnvironment
import com.minihaskell.memory.Gama

class LetExpression( val id : String, 
                     val namedExpression : Expression, 
                     val body : Expression ) extends Expression {

  Gama.insert( id, namedExpression.evalType() )
  
  override def eval(): Value = {
    val value = namedExpression.eval()
    RunningEnvironment.update( id, value )
    return body.eval()
  }

  override def evalType(): Type = {
    return body.evalType()
  }

}