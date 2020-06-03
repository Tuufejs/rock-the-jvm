package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int)
  // 1. class parameters are fields
  val jim = new Person ("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  // if I print jim, it will be automatically tostring
  //println(instance) = println(instance.toString)
  println(jim)
  println(jim.toString)

  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim==jim2)
  // if only class and not case class, then it would have been false

  // 4. case classes have handy copy method
  val jim3 = jim.copy(age = 45) // different value for age

  // 5. case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. case classes are serializable
  // Akka / messages case classes

  // 7. case classes have extractor patterns = case classes can be used in Pattern matching

  case object UnitedKingdom{
    def name: String = "The UK of GB and NI"
  }

  /*
    expand MyList / use case classes and case objects
    
   */
}
