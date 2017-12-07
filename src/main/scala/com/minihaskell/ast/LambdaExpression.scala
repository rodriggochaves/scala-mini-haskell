package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment

class LambdaExpression(val id: String, val t: Type, val body: Expression)
  extends Expression {

  override def eval(): Value =
    Closure(id, ErrorType, body, RunningEnvironment.getCurrent)
  override def evalType(): Type = body.evalType()
}
