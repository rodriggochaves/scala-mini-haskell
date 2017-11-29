package com.minihaskell.memory

import scala.collection.mutable

import com.minihaskell.Type

object Gama {

  private val table = new mutable.HashMap[String, Type]()

  def find( symbol: String ): Type = {
    return table(symbol)
  }

  def insert( symbol: String, tipe: Type ): Unit = {
    table += ( symbol -> tipe )
  }

}