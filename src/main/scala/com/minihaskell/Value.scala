package com.minihaskell

trait Value extends Expression {  }

abstract class ConcreteValue[T]( val v : T ) extends Value {
  def eval(): Value = return this
}
