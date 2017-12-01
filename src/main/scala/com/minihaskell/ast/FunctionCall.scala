package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment
import com.minihaskell.exceptions.InvalidExpressionException

case class FunctionCall(fun: Expression, args: List[Expression])
  extends Expression {

  override def eval(): Value = {
    val funVal = fun.eval()

    funVal match {
      case Closure(param, body, env) => {
        RunningEnvironment.create(env)
        /* TODO: Throw if args has more than 1 element */
        RunningEnvironment.update(param, args.head.eval())
        val res = body.eval()
        RunningEnvironment.remove()
        return res
      }
      case _ => throw new InvalidExpressionException
    }
  }

}
