package indi.zeroornull.de.tuotuo.kotlin_jike.trycatch

import kotlinx.coroutines.*
import kotlinx.coroutines.time.delay

fun main() = runBlocking {
    try {
        launch {
            delay(100L)
            1 / 0
        }
    } catch (e: ArithmeticException) {
        println("Catch: $e")
    }

    delay(500L)
    println("End")
}