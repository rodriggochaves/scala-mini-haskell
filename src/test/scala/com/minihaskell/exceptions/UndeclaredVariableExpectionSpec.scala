package com.minihaskell.exceptions

import org.scalatest._
import com.minihaskell.ast._
import com.minihaskell.memory.RunningEnvironment

class LetExpSpec extends FlatSpec with Matchers {

  it should "( let x = 10 in x + y ) raise Exception" in {
    RunningEnvironment.clean()
    var let = new LetExpression("x", IntValue(10),
      AddExpression(ReferenceExpression("x"), ReferenceExpression("y")))

    intercept[UndeclaredVariableException] {
      let.eval()
    }
  }

}
