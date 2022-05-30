package lectures.part2oop

object Generics extends App {

  // also can be on traits
  class MyList[+A] {
    // use the type A
    // [B>:A] super type of A
    def add[B>:A](element: B): MyList[B] = ???
  }

  class MyMap[K, V]

  val listOfInt = new MyList[Int]
  val listOfStr = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  //1- yes List[Cat] extends List[Animal] = covariance
  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? Hard question => we return a list of animals

  // 2- No = Invariance
  class InvarianceList[A]

  val invarianceAnimalList: InvarianceList[Animal] = new InvarianceList[Animal]

  // 3- Hell, no! Contravariance
  class ContravariantList[-A]

  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  // A <: only accept subtypes of Animal
  class Cage[A <: Animal](animal: Animal)

  val cage = new Cage(new Dog)

//  class Car
//
//  val newCage = new Cage(new Car)

  // expand MyList to be generic

}
