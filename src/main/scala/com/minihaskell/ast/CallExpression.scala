package com.minihaskell.ast

import com.minihaskell.memory.RunningEnvironment
import com.minihaskell.exceptions.InvalidExpressionException

case class CallExpression(fun: Expression, args: List[Expression])
  extends Expression {

  override def eval(): Value = {
    val funVal = fun.eval()

    funVal match {
      case Closure(param, ty, body, env) => execute(Map(param->ty), body, env)
      case Function(_, formal, body)     => execute(formal, body, Map())
      case _ => throw new InvalidExpressionException
    }
  }

  private def execute(formal: Map[String, Type],
                      body: Expression,
                      env: Map[String, Value]): Value = {
    RunningEnvironment.create(env)
    prepareEnv(formal)
    val res = body.eval()
    RunningEnvironment.remove()
    res
  }

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

  override def evalType(): Type = {
    val funVal = fun.evalType()

    funVal match {
      case FnType(from, to) => {
        if (args.length != from.length) {
          return ErrorType
        }
        else {
          for ((formal, actual) <- (from zip args)) {
            if(actual.evalType() != formal){
              return ErrorType
            }
          }
          return to
        }
      }
      case _ => return ErrorType
    }

  }
}
