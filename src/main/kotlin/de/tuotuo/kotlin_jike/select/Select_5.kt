package indi.zeroornull.de.tuotuo.kotlin_jike.select

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    suspend fun <T> fastest(vararg deferreds: Deferred<T>): T = select {
        fun cancelAll() = deferreds.forEach { it.cancel() }

        for (deferred in deferreds) {
            deferred.onAwait {
                cancelAll()
                it
            }
        }
    }

    val deferred1 = async {
        delay(100L)
        println("done1")
        "result1"
    }
    val deferred2 = async {
        delay(50L)
        println("done2")
        "result2"
    }
    val deferred3 = async {
        delay(10000L)
        println("done3")
        "result3"
    }
    val deferred4 = async {
        delay(2000L)
        println("done4")
        "result4"
    }
    val deferred5 = async {
        delay(14000L)
        println("done5")
        "result5"
    }
    val result = fastest(deferred1, deferred2, deferred3, deferred4, deferred5)
    println(result)
}
