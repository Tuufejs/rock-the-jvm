package exercises

import java.util.function.Predicate

import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource
import lectures.part2oop.Generics.MyList

abstract class MyList [+A]{
  /*
      head = first element of the list
      tail = remainder of the list
      isEmpty = is this list empty
      add(int) => new list with this element added
      toString => a string representation of the list
   */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B>:A](newItem: B):  MyList[B]
  //def toString: Unit
  def printElements: String

  override def toString: String = "[" + printElements + "]"

//  def map[B](transformer: MyTransformer[A,B]): MyList[B]
//  def filter(predicate: MyPredicate[A]): MyList[A]
//  def flatMap[B](transformer: MyTransformer[A,MyList[B]]): MyList[B]

  //Higher order functions
  def map[B](transformer: A=>B): MyList[B]
  def filter(predicate: A =>Boolean): MyList[A]
  def flatMap[B](transformer: A=>MyList[B]): MyList[B]


  //concatenation
  def ++ [B>:A](list: MyList[B]):MyList[B]

  // HOF
  def foreach(f: A => Unit): Unit

  def sort(compare: (A,A) => Int): MyList[A]

  def zipWith[B,C](list: MyList[B], zip: (A, B)=> C): MyList[C]
  def fold[B](start: B)(operator: (B,A)=> B): B
  /*
     1. Expand MyList
       - foreach method A => Unit
       [1,2,3].foreach(x =>println(x))

       - sort function ((A, A)=> Int )=> MyList
       [1,2,3].sort((x,y) => y - x) => [3,2,1]

       - zipWith(list, (A,A) => B) => MyList(B)
       [1,2,3].zipwith([4,5,6], x*y) => [1*4, 2*5, 3*6]

       -fold(start)(function) => value
  */

}
case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B>:Nothing](newItem: B):  MyList[B] = new Cons(newItem, Empty)
  def printElements: String = ""
//
//  def map[B](transformer: MyTransformer[Nothing,B]): MyList[B] = Empty
//  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
//  def flatMap[B](transformer: MyTransformer[Nothing,MyList[B]]): MyList[B] = Empty
  def map[B](transformer: Nothing=>B): MyList[B] = Empty
  def filter(predicate: Nothing=>Boolean): MyList[Nothing] = Empty
  def flatMap[B](transformer: Nothing=>MyList[B]): MyList[B] = Empty

  //concatenation
  def ++ [B>:Nothing](list: MyList[B]):MyList[B] = list

  // hof
  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing,Nothing) => Int) = Empty

  def zipWith[B, C](list: MyList[B], zip: (Nothing, B)=> C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException(" Lists do not have the same lenght")
    else Empty

  def fold[B](start: B)(operator: (B,Nothing)=> B): B = start



}
case class Cons[+A](h: A, t: MyList[A]) extends MyList[A]{
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B>:A](newItem: B):  MyList[B] = new Cons(newItem, this)
  def printElements: String =
    if (t.isEmpty) ""+ h
    else h + " " + t.printElements

//  def filter(predicate: MyPredicate[A]): MyList[A] =
//    if (predicate.test(h)) new Cons(h, t.filter(predicate))
//    else t.filter(predicate)
//
//  def map[B](transformer: MyTransformer[A,B]): MyList[B] =
//    new Cons(transformer.transform(h), t.map(transformer))
def filter(predicate: A=>Boolean): MyList[A] =
  if (predicate.apply(h)) new Cons(h, t.filter(predicate))
  else t.filter(predicate)

  def map[B](transformer: A=>B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  //concatenation
  def ++ [B>:A](list: MyList[B]):MyList[B] =
    new Cons(h, t ++ list)

//  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]=
//    transformer.transform(h) ++ t.flatMap(transformer)
  def flatMap[B](transformer: A=> MyList[B]): MyList[B]=
    transformer(h) ++ t.flatMap(transformer)

  // hof
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare:(A,A)=> Int): MyList[A]= {
    def insert(x:A, sortedList:MyList[A]): MyList[A]=
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B)=> C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException(" Lists do not have the same lenght")
    else new Cons(zip(h, list.head), t.zipWith(list.tail,zip))

  /*
    [1,2,3].fold(0)(+) = [2,3].fold(1)(+)=[3].fold(3)(+) = [].fold(6)(+) = 6
   */
  def fold[B](start: B)(operator: (B,A)=> B): B = {
    t.fold(operator(start, h))(operator)
  }




}


  /*
     1. Generic Trait MyPredicate[-T] with a little method test[T] => Boolean
     2. Generic trait MyTransformer[-A,B] with a method transforms[A] =>B
     3. MyList:
       - map(transformer) => MyList
       - filter(predicate) => MyList
       - flatMap(transformer from A to MyList[B] => MyList[B]

       class EvenPredicate extends MyPredicate[Int]
       class StringToIntTransformer extends MyTransformer [String, Int]

       [1,2,3].map(n * 2) = 2,4,6
       [1,2,3,4].filter(n%2) = [2,4]
       [1,2,3].flatmap(n => [n,n+1]) => [1,2,2,3,3,4]        */

//commented after functional programing
//trait MyPredicate[-T]{ // =>Boolean
//  def test(elem:T): Boolean
//
//}
//trait MyTransformer[-A,B]{
//  def transform(elem:A): B
//}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, Empty))
  println(list.tail.head)
  println(list.add(4).head)

  //polymorphism call
  print(list.toString)

  val listOfIntegers: MyList[Int] = new Cons(1,new Cons(2, new Cons(3,Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello",new Cons("Scala",Empty))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  println("next")
  println(listOfIntegers)

//  println(listOfIntegers.map(new Function1[Int, Int]{
//      override def apply(elem: Int) = elem * 2
//  }).toString)
  println(listOfIntegers.map(elem => elem * 2).toString)
//  println(listOfIntegers.map(_ * 2).toString)

//  println(listOfIntegers.filter(new Function1[Int, Boolean]{
//    override def apply(elem: Int): Boolean = elem % 3 == 0
//  }).toString)
  println(listOfIntegers.filter(elem => elem % 3 == 0).toString)

//  listOfIntegers.foreach(x => println(x))
  listOfIntegers.foreach(println)

  println(listOfIntegers.sort((x,y) => y - x))

  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _)) // underscore are contextual, need to specify types

  println(listOfIntegers.fold(0)(_ + _))

  // for comprehension
  val combination = for {
    n <- listOfIntegers
    c <- listOfStrings
  } yield n + "-" + c

  println(combination)
}