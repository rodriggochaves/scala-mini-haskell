package com.minihaskell.ast

import com.minihaskell.memory.{Gama, RunningEnvironment}

class LambdaExpression(val id: String, val t: Type, val body: Expression)
  extends Expression {

  override def eval(): Value =
    Closure(id, t, body, RunningEnvironment.getCurrent)
  override def evalType(): Type = {
    Gama.insert(id, t)
    FnType(t :: Nil, body.evalType())
  }
}
