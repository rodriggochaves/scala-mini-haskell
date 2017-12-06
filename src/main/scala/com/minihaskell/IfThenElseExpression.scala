package com.minihaskell
import com.minihaskell.exceptions.InvalidExpressionException

case class IfThenElseExpression( cond: Expression, then: Expression, _else: Expression ) 
     extends Expression{

  override def eval(): Value = {
    cond.eval() match{
      case BooleanValue(res) => {
        if(res){
          then.eval()
        }
        else{
          _else.eval()
        }
      }
      case _ => throw new InvalidExpressionException
    }
  }

  override def evalType(): Type = {
    val res = cond.eval().asInstanceOf[Boolean]

    if(res){
      return then.evalType()
    }
    else{
      return _else.evalType()
    }
  }


}