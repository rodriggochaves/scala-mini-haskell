package com.minihaskell.ast

// Arithmetic Expressions ------------------------------------------------------

class AddExpression(val lhs: Expression, val rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value + v2.value)
  }
}

class SubExpression(val lhs: Expression, val rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value - v2.value)
  }
}

class MultiplicationExpression(val lhs: Expression, val rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value * v2.value)
  }
}

class DivisionExpression(val lhs: Expression, val rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value / v2.value)
  }
}

// Boolean Expressions ---------------------------------------------------------

class AndExpression(val lhs: Expression, val rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[BooleanValue]
    val v2 = rhs.eval().asInstanceOf[BooleanValue]

    BooleanValue(v1.value && v2.value)
  }
}

class OrExpression(val lhs: Expression, val rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[BooleanValue]
    val v2 = rhs.eval().asInstanceOf[BooleanValue]

    BooleanValue(v1.value || v2.value)
  }
}

case class NotExpression(exp: Expression) extends Expression {

  override def eval(): Value = exp.eval() match {
    case BooleanValue(bool) => BooleanValue(!bool)
    case _                  => throw new Exception("oops")
  }
}
