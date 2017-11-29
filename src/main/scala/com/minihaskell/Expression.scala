package com.minihaskell

trait Expression {
  def eval() : Value

  def evalType(): Type
}