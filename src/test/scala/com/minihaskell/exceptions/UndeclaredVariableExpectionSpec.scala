package com.minihaskell.exceptions

import org.scalatest._
import com.minihaskell._
import com.minihaskell.exceptions.UndeclaredVariableException

class LetExpSpec extends FlatSpec with Matchers {

  it should "( let x = 10 in x + y ) raise Exception" in {
    var let = new LetExpression("x", IntValue(10), 
      new SumExpression(new ReferenceExpression("x"), new ReferenceExpression("y")))

    intercept[UndeclaredVariableException] {
      let.eval()
    }
  }

}