package lectures.part4pm

object PatternEverywhere extends App {
  //big idea # 1
  try {
    // code
  } catch {
    case e: RuntimeException => "Runtime"
    case npe: NullPointerException => "npe"
    case _ => "Something else"
  }

  // catches are actually matches
  /*
  *   try {
    // code
  } catch {
    case e: RuntimeException => "Runtime"
    case npe: NullPointerException => "npe"
    case _ => "Something else"
  }
  * */

  // big idea # 2
  val aList = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- aList if x % 2 == 0
  } yield 10 * x
  // generators are also based on pattern matching
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // big idea # 3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  // multiple values definitions based on pattern matching
  // all the power

  val head :: tail = aList

  // big idea # 4
  // partial functions
  val mappedList = aList.map {
    case v if v % 2 == 0 => v + " : is even"
    case 1 => "one"
    case _ => "something else"
  }
  val mappedList2 = aList.map { v =>
    v match {
      case v if v % 2 == 0 => v + " : is even"
      case 1 => "one"
      case _ => "something else"
    }
  }
}
