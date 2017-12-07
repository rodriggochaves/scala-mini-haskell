package com.minihaskell.ast

// Arithmetic Expressions ------------------------------------------------------

class AddExpression(val lhs: Expression, val rhs: Expression)
  extends Expression {

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

class SubExpression(val lhs: Expression, val rhs: Expression)
  extends Expression {

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

class MultiplicationExpression(val lhs: Expression, val rhs: Expression)
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

class DivisionExpression(val lhs: Expression, val rhs: Expression)
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

class AndExpression(val lhs: Expression, val rhs: Expression)
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

class OrExpression(val lhs: Expression, val rhs: Expression)
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
