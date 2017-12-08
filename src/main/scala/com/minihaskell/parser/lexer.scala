package com.minihaskell.parser

import scala.util.parsing.combinator._

object Lexer extends RegexParsers {
  override def skipWhitespace = true
  override val whiteSpace = "[ \t\r\f]+".r

  def apply(code: String): Either[(Int, Int), List[Token]] = {
    parse(tokens, code) match {
      case NoSuccess(msg, next)  => Left((next.pos.line, next.pos.column))
      case Success(result, next) => Right(result)
    }
  }

  def tokens: Parser[List[Token]] = {
    phrase(rep1(
        let
      | in
      | _if
      | _then
      | _else
      | _true
      | _false
      | int
      | bool
      | colon
      | semi_colon
      | arrow
      | or
      | equal
      | and
      | not
      | div
      | mult
      | sub
      | add
      | number
      | identifier
      | open_paren
      | close_paren
    ))
  }

  def identifier: Parser[IDENTIFIER] = {
    "[a-zA-Z_][a-zA-Z0-9_]*".r ^^ { str => IDENTIFIER(str) }
  }

  def number: Parser[NUMBER] = {
    "[0-9]+".r ^^ { str => NUMBER(str) }
  }

  def let           = "let"           ^^ { _ => LET }
  def in            = "in"            ^^ { _ => IN }
  def _if           = "if"            ^^ { _ => IF }
  def _then         = "then"          ^^ { _ => THEN }
  def _else         = "else"          ^^ { _ => ELSE }
  def arrow         = "->"            ^^ { _ => ARROW }
  def or            = "||"            ^^ { _ => OR }
  def equal         = "="             ^^ { _ => EQUAL }
  def and           = "&&"            ^^ { _ => AND }
  def not           = "!"             ^^ { _ => NOT }
  def div           = "/"             ^^ { _ => DIV }
  def mult          = "*"             ^^ { _ => MULT }
  def sub           = "-"             ^^ { _ => SUB }
  def add           = "+"             ^^ { _ => ADD }
  def _true         = "true"          ^^ { _ => TRUE }
  def _false        = "false"         ^^ { _ => FALSE }
  def open_paren    = "("             ^^ { _ => OPEN_PAREN }
  def close_paren   = ")"             ^^ { _ => CLOSE_PAREN }
  def colon         = ":"             ^^ { _ => COLON }
  def semi_colon    = ";"             ^^ { _ => SEMI_COLON }
  def int           = "Int"           ^^ { _ => INT }
  def bool          = "Bool"          ^^ { _ => BOOL }
  def less_than     = "<"             ^^ { _ => LESS_THAN }
  def greater_than  = ">"             ^^ { _ => GREATER_THAN }
  def less_than_or_equal    = "<="    ^^ { _ => LESS_THAN_OR_EQUAL }
  def greater_than_or_equal = ">="    ^^ { _ => GREATER_THAN_OR_EQUAL }
}
