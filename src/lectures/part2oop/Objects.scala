package lectures.part2oop

object Objects {
  // Scala does not have class-level Functionality ("static")
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("sd")
  }

  class Person(val name: String = "") {
    // instance-level functionality
  }


  // scala application = Scala object with
  def main(args: Array[String]): Unit = {
    // Companions
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala Object = Singleton Instance
    val mery = Person
    val jhon = Person

    println(mery == jhon)
    // Scala Object = Singleton Instance
    val m = new Person
    val j = new Person

    println(m == j)

    val bobby = Person(m, j)
  }

}
