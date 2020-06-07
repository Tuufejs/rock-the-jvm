package lectures.part2oop



import playground.{Cinderella, PrinceCharming}
//import playground._
//import playground.{Cinderella => Princess, PrinceCharming} // renaming cinderella to princess for uses
// useful when I need to import more clases with the saem name

import java.util.Date
import java.sql.{Date=> SqlDate}


object PackagingAndImport extends App{
  // package members are accessible by their simple names
  val writer = new Writer("Daniel", "RockJVM", 1954)

  val princess = new Cinderella
  //val princess = new playground.Cinderella //playground.Cinderella - fully qualified name

  //packages are in hierarchy
  //matching folder structure

  // package object
  sayHello
  println(speedOfLight)

  // imports
  val prince = new PrinceCharming

  // 1. use FQ names
  val date = new Date
  // val sqlDate = new java.sql.Date(2018,3,20)
  val sqlDate = new SqlDate(2018,3,20)

  //default imports
  // java.lang / String, Object, Exceptions
  //scala / Int, Nothing, Functions
}
