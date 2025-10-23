class MathUtils {
    fun add(a: Int, b: Int): Int {
        return a + b
    }
}

fun MathUtils.multiply(a: Int, b: Int): Int {
    return a * b
}

fun MathUtils.subtract(a: Int, b: Int): Int {
    return a - b
}

fun String.capitalizeWords(): String {
    return this.split(" ").joinToString(" ") { it.capitalize() }
}

fun main() {
    val mathUtils = MathUtils()
    val sum = mathUtils.add(3, 5)
    val product = mathUtils.multiply(3, 5)

    println("Sum: $sum")         // Output: Sum: 8
    println("Product: $product") // Output: Product: 15

    val sentence: String = "hello world from kotlin"
    val capitalizedSentence = sentence.capitalizeWords()
    println("Capitalized: $capitalizedSentence") // Output: Capitalized: Hello World From Kotlin
}
