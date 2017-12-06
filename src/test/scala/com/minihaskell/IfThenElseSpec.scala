package com.minihaskell

import org.scalatest._

class IfThenElseSpec extends FlatSpec with Matchers {
    
  it should "be evaluated to Value(25) when false && true then 5 + 5 else 5 * 5" in {
    val comp = new IfThenElseExpression(new AndExpression(BooleanValue(false), BooleanValue(true)), 
                                        new SumExpression(IntValue(5), IntValue(5)),
                                        new MultiplicationExpression(IntValue(5), IntValue(5)))
    val res = comp.eval().asInstanceOf[IntValue]

    res should be (IntValue(25))
  }

  it should "be evaluated to IntegerType() when if(true) then 5 else false " in {
    val comp = new IfThenElseExpression( BooleanValue(true), IntValue(5), BooleanValue(false) )

    comp.evalType() should be (IntegerType())
  }
}