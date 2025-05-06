package indi.zeroornull.de.tuotuo.kotlin_jike.trycatch

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    var deferred: Deferred<Unit>? = null
        deferred = async {
            delay(100L)
            1 / 0
    }
    try {
        deferred?.await()
    } catch (e: ArithmeticException) {
        println("Error: ${e.message}")
    }

    delay(500L)
    println("End")
}