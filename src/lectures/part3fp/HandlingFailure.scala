package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  // create success & failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Failure"))


  def unsafeMethod(): String = throw new RuntimeException("No string for you")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  val anotherPotentialFailure = Try {
    // code  that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "a valid string"

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  //  if you design the api
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  def betterBackupMethod(): Try[String] = Success("A valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  // map flatmap, filter
//  println(aSuccess.map(_ * 2))
//  println(aSuccess.flatMap(x => Success(x * 10)))
//  println(aSuccess.filter(_ > 10))

  // for - comprehensions
  val host = "localhost"
  val port = "8080"

  def renderHtml(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>....</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port interrupted")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))
  }

  // if you get the html page from the connection , print it to the console i.e call
  // render html

//  val possibleConnection = HttpService.getSafeConnection(host, port)
//  val possibleHtml = possibleConnection.flatMap(connection => connection.getSafe("/home"))
//  possibleHtml.foreach(renderHtml)

  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHtml(html)

}
