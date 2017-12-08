package com.minihaskell.ast

import org.scalatest._

class IfThenElseSpec extends FlatSpec with Matchers {

  it should "be evaluated to IntValue(25) when if true && false then 5 + 5 else 5 * 5" in {
    val comp = new IfThenElseExpression(
      AndExpression(BooleanValue(true), BooleanValue(false)),
      AddExpression(IntValue(5), IntValue(5)),
      MultiplicationExpression(IntValue(5), IntValue(5))
    )
    val res = comp.eval().asInstanceOf[IntValue]

    res should be (IntValue(25))
  }

  it should "be evaluated to IntValue(10) when if true || false then 5 + 5 else 5 * 5" in {
    val comp = new IfThenElseExpression(
      OrExpression(BooleanValue(true), BooleanValue(false)),
      AddExpression(IntValue(5), IntValue(5)),
      MultiplicationExpression(IntValue(5), IntValue(5))
    )
    val res = comp.eval().asInstanceOf[IntValue]

    res should be (IntValue(10))
  }

  it should "be evaluated to IntegerType when if(true) then 5 else false " in {
    val comp = new IfThenElseExpression( BooleanValue(true), IntValue(5), BooleanValue(false) )

    comp.evalType() should be (IntegerType)
  }
}
