package com.minihaskell

import org.scalatest._

class NotExpressionSpec extends FlatSpec with Matchers {

  it should "!false == true" in {
    NotExpression(BooleanValue(true)).eval() should be (BooleanValue(false))
  }

  it should "!true == false" in {
    NotExpression(BooleanValue(false)).eval() should be (BooleanValue(true))
  }
}
