
fun main() {
  // JavaClass.greet("Hendrix")
  //

  // variables
  // two ways to declare variables. val and var
  // val is immutable (cannot be changed)
  val name = "Hendrix"
  // name = "New Name" // error
  //
  // var is mutable (can be changed)
  var age = 25
  age = 26 // ok

  // control flow
  // if-else
  if (age < 18) {
    println("$name is a minor.")
  } else {
    println("$name is an adult.")
  }

  // if as an expression
  val isAdult = if (age >= 18) true else false

  // when (like switch in other languages)
  when (age) {
    in 0..12 -> println("$name is a child.")
    in 13..19 -> println("$name is a teenager.")
    else -> println("$name is an adult.")
  }

  // when as an expression
  val ageGroup = when (age) {
    in 0..12 -> "child"
    in 13..19 -> "teenager"
    else -> "adult"
  }

  /// loops
  // for loop
  for (i in 1..5) {
    println("Iteration $i")
  }

  // step and countdown
  for (i in 10 downTo 1 step 2) {
    println("Countdown: $i")
  }

  val fruits = listOf("Apple", "Banana", "Cherry")
  // iterate over a collection
  for (fruit in fruits) {
    println("Fruit: $fruit")
  }

  // iterate with index
  for ((index, fruit) in fruits.withIndex()) {
    println("Fruit at index $index is $fruit")
  }

  // while loop
  var count = 1
  while (count <= 5) {
    println("Count: $count")
    count++
  }


}

// functions
//
// fun addNumbers(a: Int, b: Int): Int {
  // return a + b
// }
// can be simplified to when the function has a single expression
fun addNumbers(a: Int, b: Int) = a + b


fun greet(name: String) {
  println("Hello, $name!")
}
