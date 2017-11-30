package com.minihaskell.memory

import com.minihaskell.ast.Value
import com.minihaskell.exceptions.UndeclaredVariableException

object RunningEnvironment {
  private var stack: List[Map[String, Value]] = Nil

  def update(variable: String, value: Value): Unit = {
    stack = stack match {
      case h :: t => h + (variable -> value) :: t
      case Nil    => Map(variable -> value):: Nil
    }
  }

  def query(variable: String): Value = stack match {
    case h :: _ => h(variable)
    case Nil    => throw new UndeclaredVariableException
  }

  def getCurrent(): Map[String, Value] = stack match {
    case h :: _ => h
    case Nil    => Map()
  }

  def remove(): Unit = {
    stack = stack.tail
  }

  def create(env: Map[String, Value]) = {
    stack = env :: stack
  }

  def clean(): Unit = {
    stack = Nil
  }
}
