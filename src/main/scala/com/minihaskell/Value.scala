package com.minihaskell

import scala.collection.mutable

trait Value extends Expression {  }

abstract class ConcreteValue[T]( val v : T ) extends Value {
  def eval(): Value = return this
}

case class Closure( id : String, body : Expression, env : mutable.HashMap[String, Value]) extends Value {
   override def eval(): Value = this

   override def evalType(): Type = body.evalType()
}
