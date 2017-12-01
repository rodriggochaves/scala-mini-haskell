package com.minihaskell.ast

import org.scalatest._
import com.minihaskell.memory.RunningEnvironment

class LambdaApplicationSpec extends FlatSpec with Matchers {

  it should "`((x) -> x + 1) , 5` == 6" in {
    RunningEnvironment.clean()
    val inc = new LambdaExpression("x", new SumExpression(new ReferenceExpression("x"), IntValue(1)))
    val app = new FunctionCall(inc, IntValue(5) :: Nil)

    app.eval() should be (IntValue(6))
  }

  it should "`( let y = 10 in let f = (x -> x + y) in let y = 20 in f(10) )` == 20" in {
    RunningEnvironment.clean()
    val let1 = new LetExpression("y", IntValue(20), FunctionCall(
                                                      ReferenceExpression("f"), IntValue(10) :: Nil ))
    val let2 = new LetExpression("f", new LambdaExpression("x",
                    new SumExpression(ReferenceExpression("x"), ReferenceExpression("y"))), let1)
    val let3 = new LetExpression("y", IntValue(10), let2)

    let3.eval() should be (IntValue(20))
  }

}
