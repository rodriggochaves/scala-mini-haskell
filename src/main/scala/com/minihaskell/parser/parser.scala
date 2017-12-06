package com.minihaskell.parser

import scala.util.parsing.combinator._
import scala.util.parsing.input.{NoPosition, Position, Reader}

import com.minihaskell.ast._

object Parser extends Parsers {
  override type Elem = Token

  class TokenReader(tokens: List[Token])
    extends Reader[Token] {

    override def first: Token = tokens.head
    override def atEnd: Boolean = tokens.isEmpty
    override def pos: Position = NoPosition
    override def rest: Reader[Token] = new TokenReader(tokens.tail)
  }

  def apply(str: String): Either[(Int, Int), Expression] = {
    Lexer(str) match {
      case Right(tokens) => apply(tokens)
      case Left(pos) => Left(pos)
    }
  }

  def apply(tokens: List[Token]): Either[(Int, Int), Expression] = {
    val reader = new TokenReader(tokens)

    program(reader) match {
      case NoSuccess(msg, next) => Left((next.pos.line, next.pos.column))
      case Success(result, next) => Right(result)
    }
  }

  def program: Parser[Expression] = {
    phrase(rep1(expression)) ^^ { case list => list.head }
  }

  def expression: Parser[Expression] = {
    lambda | let | add | sub | mult | div | number | identifier
  }

  def add: Parser[AddExpression] = {
    (operand ~ ADD ~ operand) ^^ {
      case lhs ~ _ ~ rhs => AddExpression(lhs, rhs)
    }
  }

  def sub: Parser[SubExpression] = {
    (operand ~ SUB ~ operand) ^^ {
      case lhs ~ _ ~ rhs => SubExpression(lhs, rhs)
    }
  }

  def mult: Parser[MultiplicationExpression] = {
    (operand ~ MULT ~ operand) ^^ {
      case lhs ~ _ ~ rhs => MultiplicationExpression(lhs, rhs)
    }
  }

  def div: Parser[DivisionExpression] = {
    (operand ~ DIV ~ operand) ^^ {
      case lhs ~ _ ~ rhs => DivisionExpression(lhs, rhs)
    }
  }

  def operand: Parser[Expression] = {
    number | identifier | paren
  }

  def paren: Parser[Expression] = {
    (OPEN_PAREN ~ expression ~ CLOSE_PAREN) ^^ {
      case _ ~ exp ~ _ => exp
    }
  }

  def let: Parser[Expression] = {
    (LET ~ identifier ~ EQUAL ~ expression ~ IN ~ expression) ^^ {
      case _ ~ id ~ _ ~ exp ~ _ ~ body =>
        new LetExpression(id.variable, exp, body)
    }
  }

  def lambda: Parser[LambdaExpression] = {
    (identifier ~ ARROW ~ expression) ^^ {
      case id ~ _ ~ body => new LambdaExpression(id.variable, body)
    }
  }

  private def identifier: Parser[ReferenceExpression] = {
    accept("identifier", { case IDENTIFIER(name) => ReferenceExpression(name) })
  }

  private def number: Parser[IntValue] = {
    accept("number", { case NUMBER(value) => IntValue(value.toInt) })
  }
}
