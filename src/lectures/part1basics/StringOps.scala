package lectures.part1basics

object StringOps extends App {
  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", " "))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a'+:aNumberString :+ 'n')
  println(str.reverse)
  println(str.take(2))


  // S-interpolators
  val name = "David"
  val age = 42
  val greeting = s"Hello, my name is $name and i am $age years old"
  val anotherGreeting = s"Hello, my name is $name and i will be turning ${age + 1} years old"

  //F/interpolators
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)
  // %s means string, %f means float

  // raw interpolator
  println(raw"This is a \n newline")
  val escaped = "this is a \n newline"
  println(raw"$escaped")

}

