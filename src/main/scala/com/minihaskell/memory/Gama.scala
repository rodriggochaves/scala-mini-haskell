package com.minihaskell.memory

import scala.collection.mutable

import com.minihaskell.ast.Type

object Gama {
  private val table = new mutable.HashMap[String, Type]()

  def find(symbol: String): Type = table(symbol)
  def insert(symbol: String, tipe: Type): Unit = table += (symbol -> tipe)
}
