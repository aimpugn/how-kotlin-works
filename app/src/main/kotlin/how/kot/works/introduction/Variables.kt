package how.kot.works.introduction

import how.kot.works.interfaces.Maybe
import how.kot.works.interfaces.Task

class Variables : Task, Maybe() {
    override fun run() {
        this can ::doIt and ::notDoIt
    }

    override fun doItList(): List<() -> Unit> {
        return listOf(
            ::typeInference,
            ::mustInitializeBeforeFirstRead,
        )
    }

    fun typeInference() {
        // `var`: mutable, 즉 수정 가능
        var a: String = "initial"
        printPossible(a)
        // `val`: immutable, 즉 수정 불가능
        val b: Int = 1
        // type 지정하지 않고 immutable 변수 선언 및 초기화
        // 컴파일러가 Int로 추론
        val c = 3
    }

    fun mustInitializeBeforeFirstRead() {
        val d: Int

        // until, random: https://stackoverflow.com/a/45685145
        d = if ((1 until 101).random() >= 50) {
            0
        } else {
            100
        }

        printPossible("d is $d")
    }

    override fun notDoItList(): List<() -> Unit>  {
        return listOf(
            ::declareWithoutInitialization
        )
    }

    fun declareWithoutInitialization() {
        // 초기화하지 않고 변수 선언
        var e: Int
        // Error: Variable 'e' must be initialized
        // println(e)
        printImossible("초기화하지 않고 선언된 변수 사용")
    }
}