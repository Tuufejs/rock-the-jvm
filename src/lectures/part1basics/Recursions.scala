package lectures.part1basics

import scala.annotation.tailrec


object Recursions extends App {
  def factorial(n: Int):Int =
    if (n <= 0) 1 else {
      println("computing factorial of "+n+"first need the factorial of "+(n-1))
      val result =n*factorial(n-1)
      print("computed factorial of "+n)
      result
    }

  println(factorial(8))

  def anotherFactorial(n: Int): Int = {
    @tailrec
    def facthelper(x:Int, acc: Int):Int =
      if (x<=1) acc
      else facthelper(x-1, x*acc) // tail recursion = use recursive call as last expresssion
    facthelper(n,1)
  }
  // when i need loop, i use tail rec

  // 1.concatenate string ntimes
  def concatString(mystring:String, n:Int): String={
    @tailrec
    def helper(x: Int, container: String): String={
      if (x <= 0) container
      else helper(x - 1, container + mystring)
    }
    helper(n, "")
  }

  println(concatString("Vrata",5))

  // 2. isprime function tail recursive






  // 3. Fibonacci function tail recursive
  def fibonacci(n: Int): Int={
    @tailrec
    def helper(actual:Int,previous:Int, position:Int):Int=
      if (position==1) actual
      else helper(previous,actual+previous,position-1)
    helper(1,1,n)
  }







  }
