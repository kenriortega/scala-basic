package lectures.part2oop

object Exceptions extends App {

  //  val x: String = null
  //  println(x.length)
  //  this ^^ will crash with a NPE
  // throwing and catching exceptions
  //  val aWeirdValue: String = throw new NullPointerException
  // throwable classes extend the Throwable class
  // Exception and error are the major throwable subtypes

  // 2 how to catch exceptions
  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    //    code that will get executed no matter that
    // use finally only for side effects
    println("finally")
  }
  println(potentialFail)

  // 3
  class MyException extends Exception

  //  val exception = new MyException
  //  throw exception

  /*
  * 1 Crash your program with an OutOfMemoryError
  * 2 Crash with SOError
  * 3 PocketCalculator
  *   - add(x,y)
  *   - sub(x,y)
  *   - mult(x,y)
  *   - div(x,y)
  *
  *  Throw
  *  - OverflowException if add(x,y) exceeds Int.MAX_VALUE
  *  - UnderflowException if sub(x,y) exceeds Int.MIN_VALUE
  *  - MathCalculationException for division by 0
  * */

  // OOM
  //  val array = Array.ofDim(Int.MaxValue)

  // SOError
  //  def infinite: Int = 1+infinite
  //  val noLimit =infinite

  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def sub(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def mult(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def div(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }
}
