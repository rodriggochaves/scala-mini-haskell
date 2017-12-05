package com.minihaskell.ast

// Arithmetic Expressions ------------------------------------------------------

case class AddExpression(lhs: Expression, rhs: Expression) extends Expression {
  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value + v2.value)
  }
}

case class SubExpression(lhs: Expression, rhs: Expression) extends Expression {
  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value - v2.value)
  }
}

case class MultiplicationExpression(lhs: Expression, rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value * v2.value)
  }
}

case class DivisionExpression(lhs: Expression, rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value / v2.value)
  }
}

// Boolean Expressions ---------------------------------------------------------

case class AndExpression(lhs: Expression, rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[BooleanValue]
    val v2 = rhs.eval().asInstanceOf[BooleanValue]

    BooleanValue(v1.value && v2.value)
  }
}

case class OrExpression(lhs: Expression, val rhs: Expression)
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
