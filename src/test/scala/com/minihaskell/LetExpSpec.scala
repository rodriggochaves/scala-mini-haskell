package com.minihaskell

import org.scalatest._

class LetExpSpec extends FlatSpec with Matchers {

  it should "be evaluated to Value(20) when let x = 10 in x + x" in {
    val let  = new LetExpression("x", IntValue(10),
      new SumExpression(new ReferenceExpression("x"), new ReferenceExpression("x")))

    let.eval() should be (IntValue(20))
  }

}