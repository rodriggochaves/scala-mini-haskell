package com.minihaskell.memory

import scala.collection.mutable

import com.minihaskell.Value

object RunningEnvironment {

  val table = new mutable.HashMap[ String, Value ]

  def update( variable: String, value: Value ): Unit = {
    table += ( variable -> value )
  }

  def query( variable: String ): Value = table( variable )

}