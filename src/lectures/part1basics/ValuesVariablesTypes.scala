package lectures.part1basics

object ValuesVariablesTypes extends App {
  // val are inmutables
  // compiler can infer type
  val x = 42
  println(x)

  val aStr: String = "hello"
  val aBool = false
  val aChar = 'a'
  val anInt = 34
  val aShort: Short = 1222
  val aLong: Long = 1111111111111111111L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4
  aVariable = 2 // side effects

}
