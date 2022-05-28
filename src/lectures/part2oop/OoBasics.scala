package lectures.part2oop

object OoBasics extends App {
  val person = new Person("kali", 23)
  println(person.age)
  println(person.x)
  person.greet("daniel")
  person.greet()

  val author = new Writer("Charles", "Dickens", 1812)
  val authorImpostor = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(author.year)
  println(author.fullName)
  println(novel.authorYear)
  println(novel.isWrittenBy(authorImpostor))

  val counter = new Counter
  counter.inc.inc.inc(12).dec.print
}

// constructor
// class parameters are not Fields you need to add val o var to parameters
class Person(name: String, val age: Int = 0) {
  // val and var definitions
  val x = 2

  // func definitions
  def greet(name: String): Unit = println(s"$name says hi to ${this.name}")

  // overloading
  def greet(): Unit = println(s"Hi, I am ${this.name}")
  // expressions
  println("sd" + 12)

  // multiple constructors
  def this(name: String) = this(name, 0)

  def this() = this("John Doe")
}

/*
* Novel and a writer
* Writer first name, surname, year
* - method full name
*
* Novel name, year of release, author
* author Age
* isWriterBy(author)
* copy (a new year of releaser) = new instance of novel
* */

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = firstName + " " + surname
}

class Novel(name: String, year: Int, author: Writer) {
  def authorYear = year - author.year

  def isWrittenBy(author: Writer) = author == this.author

  def copy(newYear: Int): Novel = new Novel(name, year, author)
}

/*
* Counter class
* receive an int value
* method current count
* method to inc/dec => new Counter
* overload inc/dec to receive an amount
* */

class Counter(val count: Int = 0) {
  def inc = {
    println("Inc")
    new Counter(count + 1)
  } // immutability

  def dec = {
    println("Dec")

    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}