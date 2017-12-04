package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment

class LambdaExpression(val id: String, val body: Expression)
  extends Expression {

  override def eval(): Value = Closure(id, body, RunningEnvironment.getCurrent)
}
