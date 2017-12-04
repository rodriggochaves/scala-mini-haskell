package com.minihaskell.ast

import org.scalatest._

class IfThenElseSpec extends FlatSpec with Matchers {
    
  it should "be evaluated to Value(25) when true && true then 5 + 5 else 5 * 5" in {
    val comp = new IfThenElseExpression(new AndExpression(BooleanValue(false), BooleanValue(true)), 
                                    new SumExpression(IntValue(5), IntValue(5)),
                                    new MultiplicationExpression(IntValue(5), IntValue(5)))
    val res = comp.eval().asInstanceOf[IntValue]

    res should be (IntValue(25))
  }
}