package indi.zeroornull.de.tuotuo.kotlin_jike.synchronous

import indi.zeroornull.de.tuotuo.kotlin_jike.coroutine.logX
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun main() = runBlocking {
    suspend fun getResult1(): String {
        logX("start getResult1")
        delay(1000L)
        logX("End getResult1")
        return "Result1"
    }

    suspend fun getResult2(): String {
        logX("start getResult2")
        delay(1000L)
        logX("End getResult2")
        return "Result2"
    }

    suspend fun getResult3(): String {
        logX("start getResult3")
        delay(1000L)
        logX("End getResult3")
        return "Result3"
    }

    var result: List<String>
    val time = measureTimeMillis {
        val result1 = async { getResult1() }
        val result2 = async { getResult2() }
        val result3 = async { getResult3() }
        result = listOf(result1.await(), result2.await(), result3.await())
    }

    println("Time taken: $time")
    println("Result is $result")

}