package com.minihaskell.ast

// Arithmetic Expressions ------------------------------------------------------

case class AddExpression(lhs: Expression, rhs: Expression) extends Expression {
  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value + v2.value)
  }

  override def evalType(): Type = {
    val t1 = lhs.evalType()
    val t2 = rhs.evalType()

    if (t1 == IntegerType && t2 == IntegerType) {
      IntegerType
    } else {
      ErrorType
    }
  }
}

case class SubExpression(lhs: Expression, rhs: Expression) extends Expression {
  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value - v2.value)
  }

  override def evalType(): Type = {
    val t1 = lhs.evalType()
    val t2 = rhs.evalType()

    if (t1 == IntegerType && t2 == IntegerType) {
      IntegerType
    } else {
      ErrorType
    }
  }
}

case class MultiplicationExpression(lhs: Expression, rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value * v2.value)
  }

  override def evalType(): Type = {
    val t1 = lhs.evalType()
    val t2 = rhs.evalType()

    if (t1 == IntegerType && t2 == IntegerType) {
      IntegerType
    } else {
      ErrorType
    }
  }
}

case class DivisionExpression(lhs: Expression, rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value / v2.value)
  }

  override def evalType(): Type = {
    val t1 = lhs.evalType()
    val t2 = rhs.evalType()

    if (t1 == IntegerType && t2 == IntegerType) {
      IntegerType
    } else {
      ErrorType
    }
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

  override def evalType(): Type = {
    val t1 = lhs.evalType()
    val t2 = rhs.evalType()

    if (t1 == BooleanType && t2 == BooleanType) {
      BooleanType
    } else {
      ErrorType
    }
  }
}

case class OrExpression(lhs: Expression, val rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[BooleanValue]
    val v2 = rhs.eval().asInstanceOf[BooleanValue]

    BooleanValue(v1.value || v2.value)
  }

  override def evalType(): Type = {
    val t1 = lhs.evalType()
    val t2 = rhs.evalType()

    if (t1 == BooleanType && t2 == BooleanType) {
      BooleanType
    } else {
      ErrorType
    }
  }
}

case class NotExpression(exp: Expression) extends Expression {
  override def eval(): Value = exp.eval() match {
    case BooleanValue(bool) => BooleanValue(!bool)
    case _                  => throw new Exception("oops")
  }

  override def evalType(): Type = {
    val t1 = exp.evalType()
    if (t1 == BooleanType) BooleanType else ErrorType
  }
}
