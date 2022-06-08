package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // seq  a very general interface for data structure that
  // - have well defined order
  // - can be indexed
  // support various operations:
  // - apply, iterator,length,reverse for indexing & iterating
  // - concatenation , appending, prepending
  // - a lot of other: grouping, sorting,zipping,searching,slicing

  val aSeq = Seq(1, 2, 3, 4)
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(5, 6, 7))
  println(aSeq.sorted)

  // Ranges
  val aRanges: Seq[Int] = 1 until 10
  aRanges.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  val aList = List(1, 2, 3)
  val prepended = 42 :: aList
  println(prepended)
  val prepended2 = 42 +: aList :+ 89
  println(prepended2)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString("- | -"))


  // arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0
  println(numbers.mkString(" "))

  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // vectors
  val vectors: Vector[Int] = Vector(1, 2, 3)
  println(vectors)
  // vectors vs lists
  val maxRuns = 1_000
  val maxCapacity = 1_000_000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}
