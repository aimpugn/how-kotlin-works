package how.kot.works.introduction

import how.kot.works.interfaces.Maybe
import how.kot.works.interfaces.Task

class NullSafety : Task, Maybe() {
    override fun run() {
        this can ::doIt and ::notDoIt
    }

    override fun doItList(): List<() -> Unit> {
        return listOf(
            ::nullable
        )
    }

    fun nullable() {
        var nullable: String? = "You can keep a null here"
        nullable = null
        printPossible("var nullable: String? 경우 `nullable = null`")
    }

    override fun notDoItList(): List<() -> Unit> {
        return listOf(
            ::nonNullable,
            ::notPassNullable,
        )
    }

    fun nonNullable() {
        var neverNull: String = "This can't be null"
        // neverNull = null
        printImossible("var neverNull: String 경우 mutalble이지만 `neverNull = null`")

        var inferredNonNull = "The compiler assumes non-null"   // 5
        // inferredNonNull = null
        printImossible("var inferred = \"something\" 처럼 초기화 된 경우, non-null로 추론되어 `inferredNonNull = null`")
    }

    fun notPassNullable() {
        printImossible("strLength(notNull: String) 경우 nullable인 String?")
    }

    fun strLength(notNull: String): Int {
        return notNull.length
    }
}