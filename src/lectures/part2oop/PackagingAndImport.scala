package lectures.part2oop

import playground.Cinderella

object PackagingAndImport extends App{
  // package members are accessible by their simple names
  val writer = new Writer("Daniel", "RockJVM", 1954)

  val princess = new Cinderella
  //val princess = new playground.Cinderella //playground.Cinderella - fully qualified name

  //packages are in hierarchy
  //matching folder structure

  // package object

}
