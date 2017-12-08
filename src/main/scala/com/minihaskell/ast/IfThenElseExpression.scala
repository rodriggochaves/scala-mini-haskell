package com.minihaskell.ast

import com.minihaskell.exceptions.InvalidExpressionException

case class IfThenElseExpression(cond: Expression, t: Expression, f: Expression)
  extends Expression {

  override def eval(): Value = cond.eval() match {
    case BooleanValue(res) => if (res) t.eval() else f.eval()
    case _                 => throw new InvalidExpressionException
  }

  override def evalType(): Type = {
    val res = cond.eval().asInstanceOf[BooleanValue]
    if (res.value) t.evalType() else f.evalType()
  }
}
