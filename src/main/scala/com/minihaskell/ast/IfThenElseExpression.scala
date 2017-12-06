package com.minihaskell.ast
import com.minihaskell.exceptions.InvalidExpressionException

case class IfThenElseExpression( cond: Expression, _then: Expression, _else: Expression )
     extends Expression{

  override def eval(): Value = {
    cond.eval() match{
      case BooleanValue(res) => {
        if(res){
          _then.eval()
        }
        else{
          _else.eval()
        }
      }
      case _ => throw new InvalidExpressionException
    }
  }

  override def evalType(): Type = {
    val res = cond.eval().asInstanceOf[BooleanValue]

    if (res.value) {
      _then.evalType()
    } else {
      _else.evalType()
    }
  }


}
