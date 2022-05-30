package lectures.part2oop

import playground.{Cinderela => Princess, PrincessCharming}
//import playground.{Cinderela, PrincessCharming}

//import playground._ // only when you need

object PackagingAndImports extends App {

  // packages members are accessible by their simple name
  val writer = new Writer("daniel", "rockJVM", 2018)

  // import the package
  val princess = new Princess

  // package are in hierarchy
  // matching folder structure

  // package object
  println(SPEED_OF_LIGHT)
  sayHello

  val princessCharming = new PrincessCharming
}
