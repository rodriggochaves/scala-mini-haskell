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

        if (args.length != 1) {
          throw new InvalidExpressionException
        }

        RunningEnvironment.update(param, args.head.eval())
        val res = body.eval()
        RunningEnvironment.remove()
        return res
      }
      case Function(_, formalArgs, body) => {
        RunningEnvironment.create(Map())
        val names = formalArgs.keySet.toList
        if (names.length != args.length) {
          throw new InvalidExpressionException
        }

        for ((formal, actual) <- (names zip args)) {
          RunningEnvironment.update(formal, actual.eval())
        }

        val res = body.eval()
        RunningEnvironment.remove()
        return res
      }
      case _ => throw new InvalidExpressionException
    }
  }

}
