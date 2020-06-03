package lectures.part2oop

object MethodNotations  extends App{
  class Person(val name:String, favoriteMovie: String, val age:Int=30){
    def likes(movie: String) :Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging with ${person.name}"
    def unary_! : String = s"$name what the eck"
    def isAlive: Boolean = true
    def apply(): String = s"hi, my name is $name and I like $favoriteMovie"


    def learns( subj:String): String = s"$name learns $subj"
    def learnScala: String = this learns "Scala"
    def +(prezdivka: String):Person  = new Person((name +" "+ prezdivka), favoriteMovie,age)
    def unary_+ :Person = new Person (name, favoriteMovie, age+1)
    def apply(n:Int=1) = s"$name watched $favoriteMovie $n times"

  }

  val mary = new Person("Mary", "Inception", 22)
  println(mary.likes("Inception"))
  println( mary likes "Inception") //infix notation = operator notation
  // this is an example of syntactic sugar


  //operators in scala
  val tom = new Person("Tom", "Fight club")
  println( mary + tom)
  println( mary.+(tom))

  //all operators are methods
  // Akka actors have ! ?

  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-

  println(!mary)
  println(mary.unary_!)

  //postfix notation / only available for methods with no params
  println(mary.isAlive)
  println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary()) // equivovalent
  println(mary.apply(2))

  println((+mary).age) // need to place into ()
  println(mary.learnScala)



  /*
  1. overload the + operator
      mary + "the rockstar" =>new person "Mary (the rockstar")

  2. add age to the person class
    add a unary + operator =>new person with the age +1
    +mary => mary with the age incrementer

  3. add a "learns" method in the Person class => "Mary learns scala"
    add a learnScala method, calls learn method with "Scala"
    use it as postfix notation

  4.overload the apply method
    mary.apply(2) => "Mary watched Inception 2 times"

   */


}
