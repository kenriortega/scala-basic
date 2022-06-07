package exercises

abstract class Mylist[+A] {

  // head = first element of the list
  def head: A

  // tail = reminder of the list
  def tail: Mylist[A]

  // isEmpty = is this list is empty
  def isEmpty: Boolean

  // add(int) => new list with this element added
  def add[B >: A](element: B): Mylist[B]

  // toString => a string representation of the list
  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // HOF higher-order functions
  // (receiver function as parameter for return function as result)
  def map[B](transformer: A => B): Mylist[B]

  def flatMap[B](transformer: A => Mylist[B]): Mylist[B]

  def filter(predicate: A => Boolean): Mylist[A]

  //
  def ++[B >: A](list: Mylist[B]): Mylist[B]
}

case object Empty extends Mylist[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: Mylist[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): Mylist[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): Mylist[B] = Empty

  def flatMap[B](transformer: Nothing => Mylist[B]): Mylist[B] = Empty

  def filter(predicate: Nothing => Boolean): Mylist[Nothing] = Empty

  override def ++[B >: Nothing](list: Mylist[B]): Mylist[B] = list
}

case class Cons[+A](h: A, t: Mylist[A]) extends Mylist[A] {
  def head: A = h

  def tail: Mylist[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): Mylist[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def filter(predicate: A => Boolean): Mylist[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): Mylist[B] =
    new Cons(transformer(h), t.map(transformer))

  def ++[B >: A](list: Mylist[B]): Mylist[B] = new Cons[B](h, t ++ list)

  def flatMap[B](transformer: A => Mylist[B]): Mylist[B] =
    transformer(h) ++ t.flatMap(transformer)
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
  val listOfIntegers: Mylist[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](17, Empty)))
  val cloneListOfIntegers: Mylist[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](17, Empty)))
  val listOfStr: Mylist[String] = new Cons[String]("hello", new Cons[String]("Scala", Empty))

  println(listOfStr.toString)
  println(listOfIntegers.toString)

  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers
    .map(new Function1[Int, Int] {
      override def apply(elem: Int): Int = elem * 2
    })
    .filter(new Function1[Int, Boolean] {
      override def apply(elem: Int): Boolean = elem % 2 == 0
    }).toString)

  val anotherListInteger: Mylist[Int] = new Cons[Int](4, new Cons[Int](5, Empty))
  println((listOfIntegers ++ anotherListInteger).toString)
  println(listOfIntegers.flatMap(new Function1[Int, Mylist[Int]] {
    override def apply(elem: Int): Mylist[Int] = new Cons[Int](elem, new Cons(elem + 1, Empty))
  }).toString)

  println(cloneListOfIntegers == listOfIntegers)
}