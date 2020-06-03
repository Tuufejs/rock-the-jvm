package lectures.part1basics

object Functions extends App {// for the code run bz intellij

  def aFunction(a: String, b: Int): String =
    a + " " + b
  println(aFunction("Hello", 4))

  def aParameterlessFunction(): Int = 42

  //can be called with and withuot ()
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String ={
    if (n==1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }
  println(aRepeatedFunction("hello",4))

  def greetingFunction(name: String, age: Int):String ={
    "Hi, my name is " + name +"and I am "+ age +" years old"
  }
  greetingFunction("Vrata",27)

  def factorial(n: Int):Int = {
    if (n <= 0) 1 else n*factorial(n-1)
  }
  println(factorial(3))
  def fibonacci(n: Int): Int={
    if (n<=1) 1
    else fibonacci(n-1)+ fibonacci(n-2)
    }
  println(fibonacci(31))

  def isPrime(n: Int): Boolean={
    def isPrimeuntil(t: Int): Boolean=
      if (t<=1) true
      else n%t != 0 && isPrimeuntil(t-1)
    isPrimeuntil(n/2)
  }
println(isPrime(35))
}
