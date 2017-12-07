package com.minihaskell.parser

sealed trait Token

case class  NUMBER(str: String)      extends Token
case class  IDENTIFIER(str: String)  extends Token
case object IF                       extends Token
case object THEN                     extends Token
case object ELSE                     extends Token
case object IN                       extends Token
case object LET                      extends Token
case object EQUAL                    extends Token
case object OR                       extends Token
case object AND                      extends Token
case object NOT                      extends Token
case object ARROW                    extends Token
case object DIV                      extends Token
case object MULT                     extends Token
case object SUB                      extends Token
case object ADD                      extends Token
case object OPEN_PAREN               extends Token
case object CLOSE_PAREN              extends Token
case object TRUE                     extends Token
case object FALSE                    extends Token
