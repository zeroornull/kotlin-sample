package indi.zeroornull.de.tuotuo.kotlin_jike.synchronous

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val deferreds = mutableListOf<Deferred<Int>>()

    repeat(10) {
        val deferred = async(Dispatchers.Default) {
            var i = 0
            repeat(1000) {
                i++
            }
            return@async i
        }
        deferreds.add(deferred)
    }
    var result = 0
    deferreds.forEach {
        result += it.await()
    }
    println("i = $result")

}

