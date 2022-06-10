package lectures.part3fp

import scala.util.Random

object Options extends App {
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None
  println(myFirstOption)
  println(noOption)

  // how to working with unsafe Apis
  def unsafeMethod(): String = null

  //  val result = Some(unsafeMethod())// Wrong
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained method
  def backupMethod(): String = "a valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // Desing unsafe API
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
  // functions options

  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UnSafe - Do not use this

  // map, flatmap,filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions
  /*
  * Exercise
  * */

  val config: Map[String, String] = Map(
    // fetched from else where ...
    "host" -> "localhost",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  //  try to establish a connection , if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  val connectionStatus = connection.map(c => c.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)

  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for - comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)

}
