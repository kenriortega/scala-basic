package lectures.part3fp

object WhatsAFunction extends App {
  // Dream: use functions as first class elements
  // problem : oop

  val doubler = new MyFunc[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  // functions types = Func[A,B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // function types Function2[A,B,R] == (A,B) => R
  // All scala functions are objects

  /*
  *
  * 1. a function which take 2 strings and concatenates them
  * 2. transform the MyPredicate & MyTransformer into function types
  * 3. define a function which take an int & returns another function which take
  * an int & return an int
  *   whats the type of this function
  *
  * */

  def concatenator:
  (String, String) => String = {
    new Function2[String, String, String] {
      override def apply(a: String, b: String): String = a + b
    }
  }

  println(concatenator("hello ", "scala"))

  // Function1[Int,Function1[Int,Int]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function
}

trait MyFunc[A, B] {
  def apply(element: A): B = ???
}
