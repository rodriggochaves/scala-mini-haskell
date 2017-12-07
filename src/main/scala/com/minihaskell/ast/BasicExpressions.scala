package com.minihaskell.ast

// Arithmetic Expressions ------------------------------------------------------

class SumExpression(val lhs: Expression, val rhs: Expression)
  extends Expression {

  override def eval(): Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]

    IntValue(v1.value + v2.value)
  }

  override def evalType(): Type = {
    val t1 = lhs.evalType()
    val t2 = rhs.evalType()

    if( t1.isInstanceOf[IntegerType] && t2.isInstanceOf[IntegerType] ) {
      return IntegerType()
    }
    return ErrorType()
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

    if( t1.isInstanceOf[IntegerType] && t2.isInstanceOf[IntegerType] ) {
      return IntegerType()
    }
    return ErrorType()
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

    if( t1.isInstanceOf[IntegerType] && t2.isInstanceOf[IntegerType] ) {
      return IntegerType()
    }
    return ErrorType()
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

    if( t1.isInstanceOf[BooleanType] && t2.isInstanceOf[BooleanType] ) {
      return BooleanType()
    }
    return ErrorType()
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

    if( t1.isInstanceOf[BooleanType] && t2.isInstanceOf[BooleanType] ) {
      return BooleanType()
    }
    return ErrorType()
  }
}

case class NotExpression(exp: Expression) extends Expression {

  override def eval(): Value = exp.eval() match {
    case BooleanValue(bool) => BooleanValue(!bool)
    case _                  => throw new Exception("oops")
  }
}
