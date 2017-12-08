package com.minihaskell.memory

import scala.collection.mutable
import java.util.NoSuchElementException

import com.minihaskell.ast.Type
import com.minihaskell.exceptions._

object Gama {
  private val table = new mutable.HashMap[String, Type]()

  def find(symbol: String): Type = try {
    table(symbol)
  } catch {
    case _: NoSuchElementException => throw new UndeclaredVariableException()
  }
  def insert(symbol: String, tipe: Type): Unit = table += (symbol -> tipe)
}
