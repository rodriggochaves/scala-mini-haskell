package com.minihaskell.ast

import org.scalatest._
import com.minihaskell.memory.RunningEnvironment

class CallExpressionSpec extends FlatSpec with Matchers {

  it should "`((x) -> x + 1) , 5` == 6" in {
    RunningEnvironment.clean()
    val inc = new LambdaExpression("x", IntegerType, new AddExpression(new ReferenceExpression("x"), IntValue(1)))
    val app = new CallExpression(inc, IntValue(5) :: Nil)

    app.eval() should be (IntValue(6))
  }

  it should "`( let y = 10 in let f = (x -> x + y) in let y = 20 in f(10) )` == 20" in {
    RunningEnvironment.clean()
    val let1 = new LetExpression("y", IntValue(20), CallExpression(
                                                      ReferenceExpression("f"), IntValue(10) :: Nil ))
    val let2 = new LetExpression("f", new LambdaExpression("x", IntegerType,
                    new AddExpression(ReferenceExpression("x"), ReferenceExpression("y"))), let1)
    val let3 = new LetExpression("y", IntValue(10), let2)

    let3.eval() should be (IntValue(20))
  }

  it should "have a descriptive title" in {
    RunningEnvironment.clean()
    val x = ReferenceExpression("x")
    val y = ReferenceExpression("y")
    val body = new AddExpression(x, y)
    Function("sqr", Map("x" -> IntegerType, "y" -> IntegerType), body).eval()
    val func = ReferenceExpression("sqr")
    val call = CallExpression(func, IntValue(10) :: IntValue(5) :: Nil)

    call.eval() should be (IntValue(15))
  }

}
