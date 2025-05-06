package indi.zeroornull.de.tuotuo.kotlin_jike.trycatch

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    launch {
        try {
            delay(100L)
            1 / 0
        } catch (e: ArithmeticException) {
            println("Catch: $e")
        }
    }

    delay(500L)
    println("End")
}