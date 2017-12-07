package com.minihaskell.ast

import org.scalatest._
import com.minihaskell.memory.RunningEnvironment

class LetExpSpec extends FlatSpec with Matchers {

  it should "be evaluated to Value(20) when let x = 10 in x + x" in {
    RunningEnvironment.clean()
    val let  = new LetExpression("x", IntValue(10),
      new SumExpression(new ReferenceExpression("x"), new ReferenceExpression("x")))

    let.eval() should be (IntValue(20))
  }

  it should "(let x = 10 in x) == 10 " in {
    RunningEnvironment.clean()
    val let = new LetExpression("x", IntValue(10), new ReferenceExpression("x"))

    let.eval() should be (IntValue(10))
  }

  it should "return the correct type" in {
    // let x = 10 in x + x
    val let  = new LetExpression("x", IntValue(10),
      new SumExpression(new ReferenceExpression("x"), new ReferenceExpression("x")))

    let.evalType() shouldBe a [IntegerType]
  }

  it should "return ErrorType if we're not typing correctly" in {
    val let  = new LetExpression("x", BooleanValue(true),
      new SumExpression(new ReferenceExpression("x"), new ReferenceExpression("x")))
    
    let.evalType() shouldBe a [ErrorType]
  }

}
