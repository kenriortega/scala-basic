package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the one"
    case 2 => "double"
    case 3 => "third"
    case _ => "something else" // _ = wildcard

  }
  println(x)
  println(description)

  // 1. decompose value
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 14)
  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, $n & i am $a i can`t drink"
    case Person(n, a) => s"Hi, $n & i am $a"
    case _ => "I don`t know who i am"
  }
  /*
  * 1. case are matched in order
  * 2. if no match any throw a MatchError
  * 3. unified type of all the type in all the cases
  * 4. PM work really well with case classes
  * */

  println(greeting)

  // PM on sealed hierarchies
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")

  animal match {
    case Dog(breed) => println(s"Matched $breed")
  }

  // match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  // better
  val isEvenCond = if (x % 2 == 0) true else false
  val isEvenNormal = x % 2 == 0

  /*Ex.*/

  trait Expr

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + "+" + show(e2)
    case Prod(e1, e2) => {
      def maybeShowParentheses(expr: Expr) = expr match {
        case Prod(_, _) => show(expr)
        case Number(_) => show(expr)
        case _ => "(" + show(expr) + ")"
      }

      maybeShowParentheses(e1) + "*" + maybeShowParentheses(e2)
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Number(2), Prod(Number(4), Sum(Number(2), Prod(Number(1), Number(10)))))))
}
