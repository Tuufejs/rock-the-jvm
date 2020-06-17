package lectures.part3fp

import scala.util.Random

object Sequences extends App{

  // seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  // ranges
//  val aRange: Seq[Int] = 1 to 10
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 3).foreach(x=> println("Hello"))
/*
   Lists
       LinearSeq
       isEmpty, head, tail is fast O(1)
       most operations are O(n): lenght, reverse

       Sealed
       two subtypes object Nil, class::
 */

  val aList = List(1,2,3)
//  val prepended = 42 :: aList // adds 42 in front of list
  val prepended = 42 +: aList :+89 // adds 42 in front of list and 89 in the back, colon is always on the side of the list
  println(prepended)

  val apples5 = List.fill(5)("Apple")
  println(apples5)
  println(aList.mkString("-|-")) // adds dash between values

  // Arrays
  val numbers = Array(1,2,3,4)
//  val threeElements = Array.ofDim[Int](3)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println)
//  println(threeElements)

  //mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString("*-*"))

  //arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq) //wrapped array?

  // vectors
  // effectively constant indexed read and write
  // fast element addition, good performance for large sizes
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs. lists

  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for{
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity),0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }
  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an elenement in the middle takes long
  println(getWriteTime(numbersList))
  // depth off the tree is small
  // needs to replace an entire 32 element chuink
  println(getWriteTime(numbersVector))




}
