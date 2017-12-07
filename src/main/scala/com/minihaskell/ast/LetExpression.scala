package com.minihaskell.ast

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
    // o tipo da expressao nomeada tem que está correto para o tipo ser o do corpo. Caso o contrario
    // o tipo do LET é ERRO.
    return body.evalType()
  }

}
