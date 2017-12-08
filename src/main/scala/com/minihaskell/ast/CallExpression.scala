package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment
import com.minihaskell.exceptions.InvalidExpressionException

case class CallExpression(fun: Expression, args: List[Expression])
  extends Expression {

  override def eval(): Value = {
    val funVal = fun.eval()

    funVal match {
      case Closure(param, _, body, env) => {
        RunningEnvironment.create(env)
        prepareEnv(param :: Nil)
        val res = body.eval()
        RunningEnvironment.remove()
        return res
      }
      case Function(_, formalArgs, body) => {
        RunningEnvironment.create(Map())
        prepareEnv(formalArgs)
        val res = body.eval()
        RunningEnvironment.remove()
        return res
      }
      case _ => throw new InvalidExpressionException
    }
  }

  override def evalType(): Type = fun.evalType()

  private def prepareEnv(names: List[String]): Unit = {
    if (names.length != args.length) {
      throw new InvalidExpressionException
    }

    for ((formal, actual) <- (names zip args)) {
      RunningEnvironment.update(formal, actual.eval())
    }
  }

  private def prepareEnv(names: Map[String, Type]): Unit =
    prepareEnv(names.keySet.toList)
}
