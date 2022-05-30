package lectures.part2oop

import lectures.part2oop.Generics.MyList

object CaseClasses extends App {
  /*
  * equals, hashCode, toString
  * */

  case class Person(name: String, age: Int)

  // 1 class parameters are fields
  val jim = new Person("JIm", 24)
  println(jim.age)
  // 2 sensible to string
  println(jim)

  // 3 equals and hasCode implemented OOTB
  val jim2 = new Person("JIm", 24)
  println(jim == jim2)

  // 4 CCs have handy copy method
  val jim3 = jim.copy(age = 26)

  // 5 CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6 CCs are serializable
  // Akka

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING
  case object UK {
    def name: String = "The UK"
  }

  //  Expand MyList - use case classes and case objects
}
