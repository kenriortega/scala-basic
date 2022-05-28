package lectures.part2oop

import scala.language.postfixOps

object MethodNotation extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def ƒ(person: Person): String = s"${this.name} is hOut ${person.name}"

    def unary_! : String = s"WTF!"


    def isAlive: Boolean = true

    def apply(): String = s"Hi my name is $name & i like $favoriteMovie"

    // exercises
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def learns(things: String) = s"$name is learning $things"

    def learnsScala = this learns " Scala"

    def apply(n: Int): String = s"Hi my name is $name & i like $favoriteMovie & i watched this movie $n times"
  }

  val mary = new Person("mary", "Inception")
  println(mary.likes("Inceptions"))
  println(mary likes "Inceptions") // infix notation  = operator notation (syntactic sugar)

  // operator in scala
  val tom = new Person("TOm", "Fight Club")
  println(mary ƒ tom)
  println(mary.ƒ(tom))
  // All operator are methods
  // Akka actors have ! ?

  // prefix notation
  // unary operator are also methods
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_prefix only work with - + ~ !
  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary())

  // Exercises

  /*
  * 1- Overload the + operator
  *  mary + "the rockstart" => new person "mary the rockstar"
  *
  * 2- Add an age to the person class
  *  add a unary + operator => new person with the age +1
  *  +mary => mary with the age inc
  *
  * 3- Add a "learns" method in the Person class => "mery learns scala"
  *   add a leansScala method , calls learns method with "Scala"
  * Use it in postfix notation
  *
  * 4- Overload the apply methods
  * mary.apply(2) => mary watched inception 2 times
  * */

  println((mary + "rockstar").apply())
  println((+mary).age)
  println(mary learnsScala)
  println(mary(12))
}
