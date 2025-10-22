data class Person(val name: String, val age: Int)

fun main() {
  val person = Person("Alice", 30)
  val olderPerson = person.copy(name = "Maria", age = 31)

  println(person)
  println(olderPerson)

  println("Are they equal? ${person == olderPerson}")

  val (name, age) = person
  println("Name: $name, Age: $age")
  println("Name of older person: ${olderPerson.component1()}")
  println("Age of older person: ${olderPerson.component2()}")

}
