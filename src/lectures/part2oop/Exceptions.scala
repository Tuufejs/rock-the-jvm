package lectures.part2oop

object Exceptions extends App{

  val x:String = null
  //println(x.length)
  //NullPointerException throws exception and nobody catches it

  // 1. How to throw exception
  //throw new NullPointerException / the same exception as before
//  val aWeirdValue = throw new NullPointerException

  // throwable classes extend Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exception
  def getInt(withException: Boolean):Int =
    if (withException) throw new RuntimeException("No int for you")
    else 42


  val potentialFail = try{
    //code that might fail
    getInt(true)
  }catch{
//    case e: RuntimeException => println("cautgh a runtime exception")
//    case e: NullPointerException => println("cautgh a runtime exception")
    case e: RuntimeException => 43
  } finally{
    //code that will get executed o matter what
    //optional
    //does not influence the return of expression
    //use finally only for side effects

    println("Finally")
  }
  println(potentialFail)

  // 3. how to define my own exception
  class MyException extends Exception
  val exception = new MyException

  throw exception
}
