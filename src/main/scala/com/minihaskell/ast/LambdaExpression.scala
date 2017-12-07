package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment

class LambdaExpression( val id: String, val argumentType: Type,  val body: Expression ) extends Expression {
  // lambda( x: T, corpo ) retorna um tipo Arrow(T -> tipo do corpo)

  override def eval(): Value = {
    return Closure(id, argumentType, body, RunningEnvironment.getCurrent)
  }

  // tipo arrow normalmente
  override def evalType(): Type = {
    return body.evalType()
  }

}
