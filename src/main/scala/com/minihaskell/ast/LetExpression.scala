package com.minihaskell.ast

import com.minihaskell.memory.{Gama, RunningEnvironment}

class LetExpression(val id: String, val exp: Expression, val body: Expression)
  extends Expression {

  Gama.insert(id, exp.evalType())

  override def eval(): Value = {
    val value = exp.eval()
    RunningEnvironment.update(id, value)
    body.eval()
  }

  // o tipo da expressao nomeada tem que está correto para o tipo ser o do
  // corpo. Caso o contrario o tipo do LET é ERRO.
  override def evalType(): Type = {
    val value = exp.evalType()

    if(value == ErrorType){
      return ErrorType
    }
    else{
      Gama.insert(id, value)
      body.evalType()
    }
    
  }
}
