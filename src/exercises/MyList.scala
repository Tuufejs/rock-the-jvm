package exercises

import java.util.function.Predicate

import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource

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
  val listOfStrings: MyList[String] = new Cons("Hello",new Cons("Scala", new Cons("vole",Empty)))
  println("next")
  println(listOfIntegers)

  println(listOfIntegers.map(new Function1[Int, Int]{
      override def apply(elem: Int) = elem * 2
  }).toString)

  println(listOfIntegers.filter(new Function1[Int, Boolean]{
    override def apply(elem: Int): Boolean = elem % 3 == 0
  }).toString)

}