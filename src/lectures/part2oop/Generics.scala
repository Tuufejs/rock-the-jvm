package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use type A - parameter
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }

  class MyMap[Key, Value]
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  //generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, list of cats extends list of animal / covariance
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog ?? hard question

    // 2. No - invariance
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. hell no = contravariance
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  //bounded types
  class Cage[A<: Animal](animal:A) {
    //class Cage accepts onl subtypes of Animal
    val cage = new Cage(new Dog)
  }
//  class Car
//  val newCage = new Cage(new Car)

  // expand MyList to be generic


}

