package lectures.part2oop

object InteritanceAndTraits extends App{

  class Animal {
    def eat = println("nommon")
    //private function / accesssed in this class only
    //protected / used only in this class and subclasses
    val creatureType = "wild"
  }

  class Cat extends Animal {
  // Animal is sueprclass of cat, cat is subclass of Animal
  //you can extends only one class at a time
    def crunch = {
      eat
      println("crunch crunch")
  }
  }
  val cat = new Cat
  cat.crunch


  // constructpors
  class Person(name: String, age: Int){
  //auxilirary constructors
    def this(name: String) = this(name, 0) // default value for age
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)
  //class Adult(name: String, age: Int, idCard: String) extends Person
  //Adult calls constructor of Person before Adult

  //overriding
  //class Dog extends Animal {
  //class Dog( override val creatureType:String ) extends Animal {
  class Dog(dogType:String) extends Animal {
    override  def eat: Unit = {
      super.eat
      println("Crunch dog")
    }
    override  val creatureType = dogType
    //override val creatureType: String = "domestic"
  }

  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad:polymorphism)

  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRIDING vs overLOADING

  // super

  //preventing overrides
  //final prevents from overriding (use on members)
  //final (use on members) / def
  // final on entire class
  // sealed class


}
