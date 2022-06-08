package lectures.part3fp

object AnonymousFunc extends App {

  // anonymous function (LAMBDA)
  val doubler: (Int => Int) = (x: Int) => x * 2

  // multiples params in lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomeThing: () => Int = () => 3

  // curly braces with lambdas
  val strToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x+1
  val niceAdder: (Int, Int) => Int = _ + _

  /*
  * 1. MyList: replace all functionX call with lambdas
  * 2. Rewrite the special adder as an anonymous function
  * */

  val superAdd = (x: Int) => (y: Int) => x + y

  println(superAdd(3)(4))
}
