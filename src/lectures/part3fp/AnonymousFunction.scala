package lectures.part3fp

object AnonymousFunction extends App{

  // anonymous function(Lambda)
//  val doubler: Int => Int = (x: Int) => x * 2
  val doubler: Int => Int = x => x * 2 // need to specify correct type

  // multiple params in labmda
  val adder: (Int, Int)=> Int = (a: Int, b: Int)=> a+b

  //no params
  val justDoSomething:() => Int = () => 3
  println(justDoSomething) //prints instance of function
  println(justDoSomething()) // prints value -call

  // curly braces with lambdas
  val stringToInt = {(str: String) =>
    str.toInt
  }

  // more syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x+1
  val niceAdder:(Int, Int) =>Int = _ + _ // equivalent to (a,b) =>a+b

    /*
        1. MyList: replace all FunctionX calls with lambdas
        2. Rewrite the "special" adder as an anonymous function
     */

  val specialAdder= (x: Int)=> (y: Int) => x+y

}
