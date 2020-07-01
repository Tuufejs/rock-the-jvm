package lectures.part3fp

import lectures.part3fp.Options.Connection

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App{

  val sSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(sSuccess)
  println(aFailure)

  def unsafeMethod():String = throw new RuntimeException("No string for you, buster")

    // tru object vie the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try{
    //code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess) // boolean

  //orElse
  def backupMethod(): String = "a valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))

  // if you designed the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  //map, flatmap, filter
  println(sSuccess.map(_ * 2))
  println(sSuccess.flatMap(x =>Success(x * 10)))
  println(sSuccess.filter(_>10))

  // exercise
  val host = "localhost"
  val port = "8080"
  def renderHTML(page:String) = println(page)

  class Connection{
    def get(url: String): String = {
      val random= new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection Interrupted")
    }
    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))

  }

  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  // shorthand
  HttpService.getSafeConnection(host,port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  //for comprehension
  for{
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } yield renderHTML(html)

}
