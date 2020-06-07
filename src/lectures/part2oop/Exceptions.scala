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

  //exercise
  // OOM
//  val array = Array.ofDim(Int.MaxValue)
  //throw new OutOfMemoryError("bohuzel")
  //SO
  //throw new StackOverflowError("stack is cool")
//  def infinite:Int = infinite + 1
//  val noLimit = infinite


//  class PocketCalculator(val x: Int,val y: Int){
//    def +(this.x, this.y):Int = try
//    {
//      this.x + this.y
//    }catch{
//      case e: this.x+this.y>Int.MaxValue
//    }
//    def -:Int = x+y
//    def *:Int = x*y
//    def /:Int = x/y
//  }

  class OverFlowException extends RuntimeException
  class UnderFlowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division By zero")
object PocketCalculator{
  def add(x:Int, y:Int):Int ={
    val result = x+y
    if (x> 0 && y>0 && result <0) throw new OverFlowException
    else if (x< 0 && y<0 && result >0) throw new UnderFlowException
    else result
  }
  def substract(x:Int, y:Int):Int ={
    val result = x-y
    if (x> 0 && y<0 && result <0) throw new OverFlowException
    else if (x< 0 && y>0 && result >0) throw new UnderFlowException
    else result
  }
  def multiply(x:Int, y:Int):Int ={
    val result = x*y
    if (x> 0 && y>0 && result <0) throw new OverFlowException
    else if (x<0 && y<0 && result <0) throw new OverFlowException
    else if (x< 0 && y>0 && result >0) throw new UnderFlowException
    else if (x>0 && y<0 && result >0) throw new UnderFlowException
    else result
  }
  def divide(x:Int, y:Int):Int ={
    if (y==0) throw new MathCalculationException
    else x/y
  }
}
  print(PocketCalculator.add(Int.MaxValue,14))
}
