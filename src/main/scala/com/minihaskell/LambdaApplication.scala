package com.minihaskell

import com.minihaskell.memory.RunningEnvironment
import com.minihaskell.exceptions.InvalidExpressionException

case class LambdaApplication(exp1: Expression, exp2: Expression) extends Expression {

  override def eval(): Value = {
    val v1 = exp1.eval()

    v1 match {
      case Closure(v, c, env) => {
        RunningEnvironment.create(env)
        RunningEnvironment.update(v, exp2.eval())
        val res = c.eval()
        RunningEnvironment.remove()
        return res
      }
      case _ => throw new InvalidExpressionException
    }
  }

}