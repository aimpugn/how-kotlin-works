package how.kot.works.interfaces

// Abstract Class: https://kotlinlang.org/docs/classes.html#abstract-classes
abstract class Maybe {
    private var lineCount = 0
    private val NONE = "NONE"

    fun doIt() : Maybe{
        println("### What you can do")
        lineCount = 1
        val list = doItList()
        when {
            list.isNotEmpty() -> {
                list.forEach { it.invoke() }
            }
            else -> println(NONE)
        }
        return this
    }

    fun notDoIt() : Maybe{
        println("### What you can NOT do")
        lineCount = 1
        val list = notDoItList()
        when {
            list.isNotEmpty() -> {
                list.forEach { it.invoke() }
            }
            else -> println(NONE)
        }
        return this
    }

    abstract fun doItList(): List<() -> Unit>
    abstract fun notDoItList(): List<() -> Unit>

    // https://stackoverflow.com/a/41888430
    // `()`: 파라미터 타입 (https://kotlinlang.org/docs/lambdas.html#function-types)
    // `-> Unit`: 리턴 타입
    //  ㄴ `Unit`: java의 void
    //  ㄴ `->`: 구분자. 함수 타입에서 매개변수와 리턴 타입을 구분.(https://stackoverflow.com/a/42646234)
    infix fun can(func: () -> Unit): Maybe {
        func.invoke()
        return this
    }

    infix fun and(func: () -> Unit): Maybe {
        return can(func)
    }

    fun printPossible(message: String) {
        printLines("$message 가능")
    }

    fun printImossible(message: String) {
        printLines("$message 불가")
    }

    private fun printLines(message: String){
        println("  [${lineCount++}] $message")
    }
}