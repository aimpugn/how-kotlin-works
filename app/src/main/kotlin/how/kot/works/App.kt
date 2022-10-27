/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package how.kot.works

import how.kot.works.introduction.Functions
import how.kot.works.interfaces.Task
import how.kot.works.introduction.NullSafety
import how.kot.works.introduction.Variables

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }

    val tasks: List<Task>
        get() = listOf(
            Functions(),
            Variables(),
            NullSafety(),
        )
}

// args는 공백으로 구별된다
// ./gradlew run --args="arg1=val1 arg2=val2"
fun main(/* args: Array<String> */) {
    App().tasks.forEach {
        val preSuffix = "=".repeat(20)
        println("$preSuffix ${it.javaClass.name} $preSuffix")
        it.run()
    }
}