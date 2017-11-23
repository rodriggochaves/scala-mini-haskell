package com.minihaskell

import org.scalatest._

class IntValueSpec extends FlatSpec with Matchers {

  it should "IntValue(20).value == 20" in {
    IntValue(20).value should be (20)
  }

}