package lectures.part1basics

object Exercises extends App{
  val x = 1 + 2
  println(x)

  println(2 + 3 + 4)

  println(!( 1 == x))
  // || ! &&

  var aVariable = 2
  aVariable = 3
  println(aVariable)

  // instructions (DO) vs. Expressions (Value)

  //If expressions
  val aCondition = true
  val aConditionedvalue = if(aCondition) 5 else 3 //if expression
  println(aConditionedvalue)

  var i = 0
  val aWhile = while (i < 10){
    println(i)
    i +=1
  }

  // EVERYTHING IN SCALA IS AN EXPRESSION

  val aWeirdValue = (aVariable = 3) // Unit void
  // side effect in scala is returning unit
  println(aWeirdValue)
  print(aWhile)

  // Code blocks - expression with the last value

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "Hello" else "goodbye"
  }

  // instructions are executed, execution aer evaluatd








}
