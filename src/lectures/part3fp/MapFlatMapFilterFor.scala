package lectures.part3fp


// visit this site for get documentation
// https://scala-lang.org/

object MapFlatMapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  // filter
  println(list.filter(_ % 2 == 0))
  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val number = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  // iterating
  val combinations = number.flatMap(n => chars.map(c => "" + c + n))
  val combinations3 = number.filter(_ % 2 == 0)
    .flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations)
  println(combinations3)
  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- number if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "=" + color
  println(forCombinations)

  for {
    n <- number
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
  * 1. MyList support for comprehensions
  * 2. A small collection of at most one element - Maybe [+T]
  *   - map , flatmap, filter
  * */

}
