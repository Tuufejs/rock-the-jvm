package lectures.part1basics

object ValueVariableTypes extends App {
  val x: Int = 42
  println(x)
  // val y: Int = "this is string"


  // vals are imutable
  // types of val are optional
  // compiler can infer types

  val aString: String = "Hello"
  val anotherstring = "goodbye"

  val asBoolean: Boolean = false
  val uChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 5462156
  val aFloat: Float = 2.0f
  val aBouble: Double = 3.14

  //variables
  var aVariable: Int = 4

  aVariable = 5 //side effects

}
