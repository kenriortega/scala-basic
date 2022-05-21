package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // expression
  println(x)
  println(2 + 3 * 2)
  // + - * / & | ^ << >> >>>(right shift with zero extension)
  println(1 == x)
  // == != < > >= <=
  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // -= /= ... side effect
  println(aVariable)

  // Instructions (DO) vs Expressions (Value)
  val aCondition = true
  val aConditionedValue = if (aCondition) 2 else 4 // If expression
  println(aConditionedValue)

  var i = 0
  while (i < 10) {
    i += 1
  }

  // note don`t use while
  // Everything in scala is an expression

  // new concept
  val aWeirdValue = (aVariable = 3) // Unit == void
  println(aWeirdValue)
  // side effect: println(), whiles, reassigning

  // code block
  val aCodeBlock = {
    val y = 2
    val x = 23

    if (y > x) "hello" else "sd"

  }

}
