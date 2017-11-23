package com.minihaskell

import org.scalatest._

class LambdaApplicationSpec extends FlatSpec with Matchers {

  it should "`((x) -> x + 1) , 5` == 6" in {
    val inc = new LambdaExpression("x", new SumExpression(new ReferenceExpression("x"), IntValue(1)))
    val app = new LambdaApplication(inc, IntValue(5))

    app.eval() should be (IntValue(6))
  }

}