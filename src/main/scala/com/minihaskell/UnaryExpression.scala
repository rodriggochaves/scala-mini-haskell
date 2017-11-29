package com.minihaskell

sealed trait UnaryExpression extends Expression

case class NotExpression(exp: Expression) extends UnaryExpression {

  override def eval(): Value = exp.eval() match {
    case BooleanValue(bool) => BooleanValue(!bool)
    case _                  => throw new Exception("oops")
  }
}
