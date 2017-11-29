package com.minihaskell.memory

import scala.collection.mutable
import com.minihaskell.Value
import com.minihaskell.exceptions.UndeclaredVariableException

object RunningEnvironment {
  private var stack: List[mutable.HashMap[String, Value]] = Nil

  def update(variable: String, value: Value): Unit = {
    checkIfStackEmpty()
    stack.head += (variable -> value)
  }

  def query(variable: String): Value = {
    if (stack.isEmpty) {
      throw new UndeclaredVariableException
    }
    stack.head(variable)
  }

  def getCurrent(): mutable.HashMap[String, Value] = {
    checkIfStackEmpty()
    stack.head
  }

  def remove(): Unit = {
    stack = stack.tail
  }

  def create(env: mutable.HashMap[String, Value]) = {
    stack = env :: stack
  }

  private def checkIfStackEmpty() {
    if (stack.isEmpty) {
      stack = new mutable.HashMap[String, Value] :: Nil
    }
  }
}
