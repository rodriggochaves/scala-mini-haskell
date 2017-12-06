package com.minihaskell

import com.minihaskell.memory.RunningEnvironment
import com.minihaskell.exceptions.InvalidExpressionException

case class LambdaApplication(exp1: Expression, exp2: Expression) extends Expression {

  // exp1 deve retornar uma expressão lambda. Caso o contrario, temos um erro.
  // exemplo:
  // let Inc = lambda(x, x + 1) in Inc 5
  //                             |-------|
  //                           LambdaApplication
  //                    Isso retorna Closure(x, x + 1)

  override def eval(): Value = {
    val v1 = exp1.eval()

    v1 match {
      case Closure(v, t, c, env) => {
        RunningEnvironment.create(env)
        RunningEnvironment.update(v, exp2.eval())
        val res = c.eval()
        RunningEnvironment.remove()
        return res
      }
      case _ => throw new InvalidExpressionException
    }
  }

  // improve this method
  override def evalType(): Type = {
    return exp2.evalType()
  }

}