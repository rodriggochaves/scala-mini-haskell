package com.minihaskell

import org.scalatest._

class LambdaApplicationSpec extends FlatSpec with Matchers {

  it should "`((x) -> x + 1) , 5` == 6" in {
    val inc = new LambdaExpression("x", new SumExpression(new ReferenceExpression("x"), IntValue(1)))
    val app = new LambdaApplication(inc, IntValue(5))

    app.eval() should be (IntValue(6))
  }

  ignore should "`( let y = 10 in let f = (x -> x + y) in let y = 20 in f(10) )` == 20" in {
    val let1 = new LetExpression("y", IntValue(20), LambdaApplication(
                                                      ReferenceExpression("f"), IntValue(10)))
    val let2 = new LetExpression("f", new LambdaExpression("x", 
                    new SumExpression(ReferenceExpression("x"), ReferenceExpression("y"))), let1)
    val let3 = new LetExpression("y", IntValue(10), let2)

    let3.eval() should be (IntValue(20))
  }

}