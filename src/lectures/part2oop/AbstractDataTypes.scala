package lectures.part2oop

object AbstractDataTypes extends App{

  //abstract
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
    //abstract classes cannot be initiated
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    def eat: Unit = println("crunch crunch") // i dont need to write override, because its clear
  }

  //traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val prefferedMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded{
    override val creatureType: String = "croc"
    def eat: Unit = "nmomnomnom"
    def eat(animal: Animal): Unit = println(s"Im a croc and I am eating ${animal.creatureType}")

  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  //traits vs abstract classes
  // 1 - trait does not have constructor params
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits = behavior, abstract class =
  // any = mother of all classes

}
