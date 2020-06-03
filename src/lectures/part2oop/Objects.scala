package lectures.part2oop

object Objects extends App{
  // scala doesnt have class level functionality (static)
  object Person {
    // static class level functionality
    val N_EYS = 2
    def canFly: Boolean = false

    def from(mother: Person, father: Person): Person = new Person("Robbie")
  }
  class Person(val name: String){
  // instance level functionality
  }
  // class and object Person are companions
  println(Person.N_EYS)
  println(Person.canFly)

  val mary = Person
  val john = Person
  println( mary == john)
  // they are the same

  val mary1 = new Person("Mary")
  val john1 = new Person( "John")
  println( mary1 == john1)
  // they are not the same instance


  // sacala applications = scala object with
  //def main(args: Array[String]):Unit
}
