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
    call | paren | ifThenElse | lambda | let | add | sub | mult | div | number | variable
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
    _true | _false | number | variable | paren
  }

  def paren: Parser[Expression] = {
    (OPEN_PAREN ~ expression ~ CLOSE_PAREN) ^^ {
      case _ ~ exp ~ _ => exp
    }
  }

  def let: Parser[Expression] = {
    (LET ~ identifier ~ EQUAL ~ expression ~ IN ~ expression) ^^ {
      case _ ~ id ~ _ ~ exp ~ _ ~ body => new LetExpression(id, exp, body)
    }
  }

  def lambda: Parser[LambdaExpression] = {
    (identifier ~ ARROW ~ expression) ^^ {
      case id ~ _ ~ body => new LambdaExpression(id, body)
    }
  }

  def ifThenElse: Parser[IfThenElseExpression] = {
    (IF ~ expression ~ THEN ~ expression ~ ELSE ~ expression) ^^ {
      case _ ~ cond ~ _ ~ _then ~ _ ~ _else  =>
        IfThenElseExpression(cond, _then, _else)
    }
  }

  def call: Parser[CallExpression] = {
    (operand ~ operand ~ operand.*) ^^ {
      case func ~ first ~ params => CallExpression(func, first::params)
    }
  }

  def variable: Parser[ReferenceExpression] = {
    (identifier) ^^ { str => ReferenceExpression(str) }
  }

  def _true: Parser[BooleanValue] = {
    (TRUE) ^^ { _ => BooleanValue(true) }
  }

  def _false: Parser[BooleanValue] = {
    (FALSE) ^^ { _ => BooleanValue(false) }
  }

  private def identifier: Parser[String] = {
    accept("identifier", { case IDENTIFIER(name) => name })
  }

  private def number: Parser[IntValue] = {
    accept("number", { case NUMBER(value) => IntValue(value.toInt) })
  }
}
