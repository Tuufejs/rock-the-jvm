package lectures.part3fp

import com.sun.corba.se.impl.orb.ParserTable.TestIIOPPrimaryToContactInfo

import scala.annotation.tailrec

object TuplesAndMaps extends App{
  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "Hello, scala") // Tupple2[Int,String] = (Int, String)
// val aTuple = Tuple2(2, "Hello, scala")
// val aTuple = (2, "Hello, scala")
  println(aTuple._1) //2
  println(aTuple.copy(_2 = "Goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()
//  val phoneBook = Map(("Jim", 555),("Daniel",789))// syntactic sugar
  val phoneBook = Map("Jim"-> 555,"Daniel"->789).withDefaultValue(-1)  // when keys not present
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim")) //locating
  println(phoneBook("Marry"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  //functionals on maps
  // map, flatmap, filter

  println(phoneBook.map(pair =>pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phoneBook.view.filterKeys(x => x.startsWith("J")))
  //mapValues
  println(phoneBook.view.mapValues(number => "0245" +number *10))

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel",555)).toMap)
  val names = List("Bob", "James", "Tom", "Jim", "John", "Mary", "Angela")
  println(names.groupBy(name => name.charAt(0)))

  // what would happen if JIM and Jim
  val phoneBook1 = Map("Jim"-> 555,"JIM"->789).withDefaultValue(-1)  // when keys not present
  println(phoneBook1.map(pair =>pair._1.toLowerCase -> pair._2))

  def add(network: Map[String ,Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String ,Set[String]], a: String, b:String): Map[String, Set[String]] ={
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
}

  def unfriend(network: Map[String ,Set[String]], a: String, b:String): Map[String, Set[String]] ={
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person:String):Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended- person
  }
  val empty:Map[String, Set[String]]=Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"),"Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network:Map[String, Set[String]]): Int =
//    network.filterKeys(k=> network(k).isEmpty).size
//    network.filter(pair => pair._2.isEmpty).size
    network.count(pair => pair._2.isEmpty)
  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network:Map[String, Set[String]], a:String, b: String):Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople:Set[String]):Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))


      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
}