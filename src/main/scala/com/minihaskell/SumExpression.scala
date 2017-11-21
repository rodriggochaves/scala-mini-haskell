package com.minihaskell

class SumExpression(lhs: Expression, rhs: Expression) extends Expression {
  
  // TODO
  override def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue]
    val v2 = rhs.eval().asInstanceOf[IntValue]
    
    return IntValue(v1.value + v2.value)
  }
}