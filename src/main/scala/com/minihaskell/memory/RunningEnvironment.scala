package com.minihaskell.memory

import scala.collection.mutable

import com.minihaskell.Value
import com.minihaskell.exceptions.UndeclaredVariableException

object RunningEnvironment {

  val stack = new mutable.Stack[mutable.HashMap[String, Value]]()

  def update( variable: String, value: Value ): Unit = {
    checkIfStackEmpty
    stack.top += ( variable -> value )
  }

  def query( variable: String ): Value = {
    if( !stack.isEmpty ) {
      return stack.top(variable)
    }
    throw new UndeclaredVariableException
  }

  def getCurrent(): mutable.HashMap[String, Value] = {
    checkIfStackEmpty
    return stack.top
  }

  def remove(): Unit = {
    stack.pop()
  }

  def create( env: mutable.HashMap[String, Value] ) = {
    stack.push(env)
  }

  private def checkIfStackEmpty() {
    if ( stack.isEmpty ) {
      stack.push(new mutable.HashMap[String, Value]())
    }
  }



}