package com.minihaskell.repl

import scala.io.StdIn
import java.util.NoSuchElementException

import com.minihaskell.parser.{Lexer, Parser}
import com.minihaskell.exceptions._
import com.minihaskell.ast._

object Main extends App {
  init

  private def init {
    try {
      parse(read.get)
    } catch {
      case _: NoSuchElementException => System.exit(0)
    }

    init
  }

  private def parse(line: String) {
    Parser(line) match {
      case Left(_)     => println("Syntax Error")
      case Right(expr) => execute(expr)
    }
  }

  private def execute(implicit expr: Expression) {
    try {
      println(s"$evalType: $eval")
    } catch {
      case _: InvalidExpressionException => println("Invalid Expression")
    }
  }

  private def evalType(implicit expr: Expression): String = {
    expr.evalType() match {
      case IntegerType  => "  Int"
      case BooleanType  => "  Bool"
      case FnType(_, _) => "  Fn"
      case ErrorType    => throw InvalidExpressionException()
    }
  }

  private def eval(implicit expr: Expression): String = expr.eval() match {
    case IntValue(n)          => n.toString
    case BooleanValue(b)      => b.toString
    case Function(name, _, _) => s"[Fn $name]"
    case Closure(_, _, _, _)  => "[Lambda]"
  }

  private def read: Option[String] = {
    var line = ""

    while (line.trim.length == 0) {
      line = StdIn.readLine("> ")
      if (line == null) return None
    }

    Some(line)
  }
}
