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

  def map[B](transformer: MyTransformer[A, B]): Mylist[B]

  def flatMap[B](transformer: MyTransformer[A, Mylist[B]]): Mylist[B]

  def filter(predicate: MyPredicate[A]): Mylist[A]

  def ++[B >: A](list: Mylist[B]): Mylist[B]
}

case object Empty extends Mylist[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: Mylist[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): Mylist[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): Mylist[B] = Empty

  def flatMap[B](transformer: MyTransformer[Nothing, Mylist[B]]): Mylist[B] = Empty

  def filter(predicate: MyPredicate[Nothing]): Mylist[Nothing] = Empty

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

  def filter(predicate: MyPredicate[A]): Mylist[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: MyTransformer[A, B]): Mylist[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  def ++[B >: A](list: Mylist[B]): Mylist[B] = new Cons[B](h, t ++ list)

  def flatMap[B](transformer: MyTransformer[A, Mylist[B]]): Mylist[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
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

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}


object ListTest extends App {
  val listOfIntegers: Mylist[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](17, Empty)))
  val cloneListOfIntegers: Mylist[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](17, Empty)))
  val listOfStr: Mylist[String] = new Cons[String]("hello", new Cons[String]("Scala", Empty))

  println(listOfStr.toString)
  println(listOfIntegers.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers
    .map(new MyTransformer[Int, Int] {
      override def transform(elem: Int): Int = elem * 2
    })
    .filter(new MyPredicate[Int] {
      override def test(elem: Int): Boolean = elem % 2 == 0
    }).toString)

  val anotherListInteger: Mylist[Int] = new Cons[Int](4, new Cons[Int](5, Empty))
  println((listOfIntegers ++ anotherListInteger).toString)
  println(listOfIntegers.flatMap(new MyTransformer[Int, Mylist[Int]] {
    override def transform(elem: Int): Mylist[Int] = new Cons[Int](elem, new Cons(elem + 1, Empty))
  }).toString)

  println(cloneListOfIntegers ==listOfIntegers)
}