package com.minihaskell.ast

trait Expression {
  def eval() : Value

  def evalType(): Type
}
