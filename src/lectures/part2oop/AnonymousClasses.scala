package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("funny")
  }

  // this is call anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("funny")
  }
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println("hi jim")
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





}
