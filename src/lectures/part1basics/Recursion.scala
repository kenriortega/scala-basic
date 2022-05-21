package lectures.part1basics

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial")
      var result = n * factorial(n - 1)
      result
    }

  //  println(factorial(10))

  //  println(factorial(5000)) // throw stackOverflow
  def anotherFactorial(n: Int): BigInt = {
    def factHelper(x: Int, acc: BigInt): BigInt =
      if (x <= 1) acc
      else factHelper(x - 1, x * acc) // tail recursions = use recursive call as the last expression


    factHelper(n, 1)
  }

  println(anotherFactorial(5_000))

  //  when you need loops use tail recursions
  /*
  * 1. Concatenate a string n time
  * 2- IsPrime
  * 3- fibonacci
  */

  def concatenateTailRec(aStr: String, n: Int, acc: String): String =
    if (n <= 0) acc
    else concatenateTailRec(aStr, n - 1, aStr + acc)

  println(concatenateTailRec("hello", 3, ""))

  def isPrime(n: BigInt): Boolean = {
    def isPrimeTailRec(t: BigInt, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailRec(n / 2, true)
  }

  println(isPrime(anotherFactorial(5_000)))
  println(isPrime(37))

  def fibonacci(n: Int): Int = {
    def fibonacciTailrec(i: Int, last: Int, nextToLoad: Int): Int =
      if (i >= n) last
      else fibonacciTailrec(i + 1, last + nextToLoad, last)

    if (n <= 2) 1
    else fibonacciTailrec(2, 1, 1)
  }

  println(fibonacci(8))
}
