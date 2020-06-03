package lectures.part2oop
import java.time.Year

object OOBasics extends App{
  val person = new Person("John", 26)
  //println(person.age)
  println(person.x)
  person.greet("Daniel")

  val artist = new Writer("John", "Steinbeck", 1954)
  val imposter = new Writer( "Karel", "Kryl", 1961)
  println(artist.fullName())
  val newNovel = new Novel("Cervena a cerna", 1983, artist)

  println(newNovel.authorAge)
  println(newNovel.isWrittenBy(imposter))

  val counter = new Counter()
  counter.inc

}


class Person(name: String, val age: Int = 0){//constructor
// class params are not fields / when I want to see age, i must specify val age!!!
  //body
  val x = 2
  println(1+3)   /// when initialized, class will be executed first, then object OOBasics

  //method
  def greet(name: String)= println(s"${this.name} says hi, $name") // when I add this, I saynasme in class, the other name is arg from greeting func

  // OVERLOADING
  def greet(): Unit = println(s"Hi, I am $name")

  //multiple constructors
  def this(name: String) = this(name,0) //initial values with age = 0
  def this() = this("john Doe")


}
/*
Novel and a Writer
writer: first name, surname, year
  -method fullname

Novel: name, year of release, author
  -authorAge
  -isWrittenBy(authod)
  -copy(new year of release) = new instance of Novel
 */
class Writer(firstName: String, surname: String, val year: Int){
  def fullName()= this.firstName +" "+ this.surname

}

class Novel(name: String,yearOfRelease: Int, author: Writer){
  def authorAge() = yearOfRelease - author.year  // val year in Writer
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear:Int) = new Novel(name,newYear,author) //name and author implicit

}
/*
Counter class
  - receives an int value
  - method current count
  - method increment/decrement =>new Counter
  - overload inc/dec to receive an amount
 */
class Counter(val count: Int = 0){
  def inc = {
    println("incrementing")
    new Counter(count + 1)
  } // cannot be modified, you return a new one

  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n:Int):Counter =
    if (n<=0) this
    else inc.inc(n-1)

  def dec(n:Int):Counter =
    if (n<=0) this
    else dec.dec(n-1)



}
