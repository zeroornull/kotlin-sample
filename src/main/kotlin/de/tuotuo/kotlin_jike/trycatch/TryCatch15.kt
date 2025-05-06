package indi.zeroornull.de.tuotuo.kotlin_jike.trycatch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val scope = CoroutineScope(SupervisorJob())

    val deferred = scope.async {
        delay(100L)
        1 / 0
    }

    try {
        deferred.await()
    } catch (e: ArithmeticException) {
        println("ArithmeticException: $e")
    }

    delay(500L)
    println("End")
}