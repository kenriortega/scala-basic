package lectures.part1basics

object StringsOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(""))
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  val number: String = "45"
  val toInt = number.toInt
  println('a' +: number :+ 'z')
  println(str.reverse)
  println(str.take(2))

  // scala-specific

  // s - interpolators
  val msg = s"My name $toInt or ${toInt + 2}"

  // F- interpolators
  val speed = 1.2f
  val myth = f"$msg \ncan be read $speed"
  println(myth)
  val scaped = "this \b\n"
  println(raw"$scaped")
}
