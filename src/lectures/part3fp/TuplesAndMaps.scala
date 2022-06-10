package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // tuples = finite ordered "list"
  val aTuple = new Tuple2(2, "hello") // Tuple2[Int,String] = (Int,String)
  val aTuple2 = (2, "hello") // Tuple2[Int,String] = (Int,String)

  println(aTuple._1)
  println(aTuple._2)
  println(aTuple.copy(_2 = "java"))
  println(aTuple.swap)

  // maps-keys-> values

  val aMap: Map[String, Int] = Map()
  val phonebook = Map(
    ("k", 555),
    ("d", 344),
    "w" -> 789
  ).withDefaultValue(-1)
  println(phonebook)
  println(phonebook("e"))
  // maps op
  println(phonebook.contains("k"))
  println(phonebook("k"))

  //  add
  val newParing = "Mary" -> 567
  val newPhonebook = phonebook + newParing
  println(newPhonebook)

  // functional on maps
  // map,flatmap,filter
  println(newPhonebook.map(pair => pair._1.toUpperCase -> pair._2))

  // filterKeys
  println(phonebook.keys.map(x => x.startsWith("k")))
  // mapValues
  println(phonebook.values.map(num => num * 10))

  // conversions to other collections
  println(phonebook.toList)
  val names = List("Bob", "James", "Angela", "Mary", "Mona", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
  * 1. what would happen if I had two original entries "JIM" -> 555 and "Jim" -> 900
  * 2. Overly simplified social network based on maps
  *   Person = String
  *   - add a person to the network
  *   - remove
  *   - friend (mutual)
  *   - unfriend
  *
  *   - number of friends of a person
  *   - person with most friends
  *   - how many people have no friends
  *   - if there is a social connection between two people (direct or not)
  *
  * */

  val ej1 = Map(
    ("Jim", 555),
    ("Daniel", 344),
    "JIM" -> 9000
  )

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {

    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))

  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Mary"))


  // jim,bob,mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.keys.count(_.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
}
