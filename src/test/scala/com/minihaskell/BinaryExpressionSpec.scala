package com.minihaskell.ast

import org.scalatest._

class BinaryExpressionSpec extends FlatSpec with Matchers {

  it should "`5 + 1` == 6" in {
    val sum = new SumExpression(IntValue(5), IntValue(1))
    val res = sum.eval().asInstanceOf[IntValue]

    res should be (IntValue(6))
  }

  it should "`5 * 3` == 15" in {
    val mul = new MultiplicationExpression(IntValue(5), IntValue(3))
    val res = mul.eval().asInstanceOf[IntValue]

    res should be (IntValue(15))
  }

  it should "`true && true` == true" in {
    val exp = new AndExpression(BooleanValue(true), BooleanValue(true))
    exp.eval().asInstanceOf[BooleanValue] should be (BooleanValue(true))
  }

  it should "`true && false` == false" in {
    val exp = new AndExpression(BooleanValue(true), BooleanValue(false))
    exp.eval().asInstanceOf[BooleanValue] should be (BooleanValue(false))
  }

  it should "`false && true` == false" in {
    val exp = new AndExpression(BooleanValue(false), BooleanValue(true))
    exp.eval().asInstanceOf[BooleanValue] should be (BooleanValue(false))
  }

  it should "`false && false` == false" in {
    val exp = new AndExpression(BooleanValue(false), BooleanValue(false))
    exp.eval().asInstanceOf[BooleanValue] should be (BooleanValue(false))
  }

  it should "`true || true` == true" in {
    val exp = new OrExpression(BooleanValue(true), BooleanValue(true))
    exp.eval().asInstanceOf[BooleanValue] should be (BooleanValue(true))
  }

  it should "`false || true` == true" in {
    val exp = new OrExpression(BooleanValue(false), BooleanValue(true))
    exp.eval().asInstanceOf[BooleanValue] should be (BooleanValue(true))
  }

  it should "`true || false` == true" in {
    val exp = new OrExpression(BooleanValue(true), BooleanValue(false))
    exp.eval().asInstanceOf[BooleanValue] should be (BooleanValue(true))
  }

  it should "`false || false` == false" in {
    val exp = new OrExpression(BooleanValue(false), BooleanValue(false))
    exp.eval().asInstanceOf[BooleanValue] should be (BooleanValue(false))
  }

}
