package indi.zeroornull.de.tuotuo.kotlin_jike.trycatch

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    var deferred: Deferred<Unit>? = async {
        delay(100L)
        1 / 0
    }

    delay(500L)
    println("End")
}