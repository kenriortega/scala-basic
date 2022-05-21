package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("sa", 1))

  def aParameterLessFunc(): Int = 34

  println(aParameterLessFunc())

  def aRepeatedFunc(aStr: String, n: Int): String = {
    if (n == 1) aStr
    else aStr + aRepeatedFunc(aStr, n - 1)
  }

  println(aRepeatedFunc("hi", 3))
  // When you need loops, use recursions

  def aFuncWithSideEffect(aStr: String): Unit = println(aStr)

  def aBigFunc(n: Int): Int = {
    def aSmallFunc(a: Int, b: Int): Int = a + b

    aSmallFunc(n, n - 1)
  }

  // 1 A greeting func (name,age)
  // 2 Factorial func 1*2*3*...n
  // 3 Fibonacci func
  // f(1) = 1
  // f(2) = 1
  // f(n) = f(n-1)+f(n-2)
  // 4 test if a number is prime

  def greet(name: String, age: Int): String = s"name: ${name} age: ${age}"

  println(greet("kali", 30))

  def factorial(n: Int): Int =
    if (n <= 0) 1
    else n * factorial(n - 1)

  println(factorial(5))

  def fibonacci(n: Int): Int =
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)

  println(fibonacci(8))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}
