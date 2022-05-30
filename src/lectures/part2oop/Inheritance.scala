package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  sealed class Animal {
    val creatureType = "wild"

    def eat = println("nom nom nom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  class Children(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat = {
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog("k9")
  dog.eat
  println(dog.creatureType)


  // type substitution
  val unKnownAnimal: Animal = new Dog("k9")
  unKnownAnimal.eat

  // overRIDING vs overLOADING
  // super
  // preventing overrides
  // 1- use final on member
  // 2- use final on the entire class
  // 3- seal the class = extend classes in this File, prevent extension in other files
}
