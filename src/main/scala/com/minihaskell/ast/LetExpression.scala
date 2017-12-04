package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment

class LetExpression(val id: String, val exp: Expression, val body: Expression)
  extends Expression {

  override def eval(): Value = {
    val value = exp.eval()
    RunningEnvironment.update(id, value)
    body.eval()
  }
}
