package com.minihaskell.ast

sealed trait BinaryExpression extends Expression

// Arithmetic Expressions ------------------------------------------------------

class SumExpression(val lhs: Expression, val rhs: Expression)
  extends BinaryExpression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value + v2.value)
  }
}

class MultiplicationExpression(val lhs: Expression, val rhs: Expression)
  extends BinaryExpression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value * v2.value)
  }
}

// Boolean Expressions ---------------------------------------------------------

class AndExpression(val lhs: Expression, val rhs: Expression)
  extends BinaryExpression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[BooleanValue]
    val v2 = rhs.eval().asInstanceOf[BooleanValue]

    BooleanValue(v1.value && v2.value)
  }
}

class OrExpression(val lhs: Expression, val rhs: Expression)
  extends BinaryExpression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[BooleanValue]
    val v2 = rhs.eval().asInstanceOf[BooleanValue]

    BooleanValue(v1.value || v2.value)
  }
}
