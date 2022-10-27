package how.kot.works.introduction

import how.kot.works.interfaces.Maybe
import how.kot.works.interfaces.Task

class Functions : Task, Maybe() {
    override fun run() {
        this can ::doIt and ::notDoIt
    }

    override fun doItList(): List<() -> Unit> {
        return listOf(
            ::defaultFunctions,
            ::infixFunctions,
            ::operatorFunctions,
        )
    }

    override fun notDoItList(): List<() -> Unit> {
        return listOf()
    }

    fun defaultFunctions() {
        printMessage(Functions::javaClass.name)
        printMessageWithPrefix(Functions::javaClass.name)
        printMessageWithPrefix(Functions::javaClass.name, "With prefix")
        printMessageWithPrefix(prefix = "Named Arguments", message = "Hello")
        printMessageWithPrefix(sum(5, 10).toString(), "sum(5, 10)")
        printMessageWithPrefix(multiply(5, 10).toString(), "multiply(5, 10)")
    }

    fun infixFunctions() {
        // Defines an infix extension function on Int.
        infix fun Int.times(str: String) = str.repeat(this)        // 1
        println(2 times "Bye ")                                    // 2

        // Creates a `Pair` by calling the infix function `to` from the standard library.
        val pair = "Ferrari" to "Katrina"                          // 3
        println(pair)

        // Here's your own implementation of `to` creatively called `onto`.
        // infix fun String.onto(other: String) = Pair(this, other)   // 4
        infix fun String.onto(other: String) = this to other   // 4
        val myPair = "McLaren" onto "Lucas"
        println(myPair)

        val sophia = Person("Sophia")
        val claudia = Person("Claudia")
        sophia likes claudia
        println("sophia likes claudia, then\n" +
                "   sophia.likedPeople: ${sophia.likedPeople}\n" +
                "   claudia.likedPeople: ${claudia.likedPeople}")
    }

    fun operatorFunctions() {
        operator fun Int.times(str: String) = str.repeat(this)       // 1
        println(2 * "Bye ")                                          // 2

        operator fun String.get(range: IntRange) = substring(range)  // 3
        val str = "Always forgive your enemies; nothing annoys them so much."
        println(str[0..14])                                          // 4
    }

    // defaultFunctions
    fun printMessage(message: String) {                               // 1
        println(message)
    }

    fun printMessageWithPrefix(message: String, prefix: String = "Info") {  // 2
        println("[$prefix] $message")
    }

    fun sum(x: Int, y: Int): Int {                                          // 3
        return x + y
    }

    fun multiply(x: Int, y: Int) = x * y

    // infixFunctions
    class Person(val name: String) {
        val likedPeople = mutableListOf<Person>()
        infix fun likes(other: Person) { likedPeople.add(other) }  // 6
    }
}