package exercises


abstract class MyList[+A] {

  // head = first element of the list
  def head: A

  // tail = reminder of the list
  def tail: MyList[A]

  // isEmpty = is this list is empty
  def isEmpty: Boolean

  // add(int) => new list with this element added
  def add[B >: A](element: B): MyList[B]

  // toString => a string representation of the list
  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // HOF higher-order functions
  // (receiver function as parameter for return function as result)
  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  //
  def ++[B >: A](list: MyList[B]): MyList[B]

  // hofs
  def foreach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // hofs
  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("List do not have the same length")
    else Empty

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons[B](h, t ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  // hofs
  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("List do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

/*
* 1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
* 2. Generic trait MyTransformer[-A,B]
* 3. MyList:
*   - map(transformer) => MyList
*   - filter(predicate) => MyList
*   - flatMap(transformer from A to MyList[B]) => MyList[B]
*
*
* class EvenPredicate extend MyPredicate[Int]
* class StringToIntTransformer extend MyTransformer[String,Int]
*
* [1,2,3].map(n*2) = [2,4,6]
* [1,2,3,4].filter(n%2) = [2,4]
* [1,2,3].flatMap(n=> [n,n+1]) => [1,2,2,3,3,4]
* */

// OOP
//trait MyPredicate[-T] { // T => Boolean
//  def test(elem: T): Boolean
//}
//
//trait MyTransformer[-A, B] { // A => B
//  def transform(elem: A): B
//}


object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](17, Empty)))
  val cloneListOfIntegers: MyList[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](17, Empty)))
  val listOfStr: MyList[String] = new Cons[String]("hello", new Cons[String]("Scala", Empty))
  val anotherListOfStr: MyList[String] = new Cons[String]("hi", new Cons[String]("kotlin", Empty))

  println(listOfStr.toString)
  println(listOfIntegers.toString)

  println(listOfIntegers.map(_ * 2).toString)

  println(listOfIntegers
    .map(_ * 2)
    .filter(_ % 2 == 0).toString)

  val anotherListInteger: MyList[Int] = new Cons[Int](4, new Cons[Int](5, Empty))
  println((listOfIntegers ++ anotherListInteger).toString)
  println(listOfIntegers.flatMap((elem: Int) => new Cons[Int](elem, Cons(elem + 1, Empty))).toString)

  println(cloneListOfIntegers == listOfIntegers)

  // hofs
  listOfIntegers.foreach(println)

  println(listOfIntegers.sort((x, y) => y - x))

  println(anotherListOfStr.zipWith[String, String](listOfStr, _ + "-" + _))

  println(listOfIntegers.fold(0)(_ + _))

  // for comprehensions
  val cb = for {
    n <- listOfIntegers
    s <- listOfStr
  } yield n + "-" + s
  println(cb)
}