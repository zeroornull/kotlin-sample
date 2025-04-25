package indi.zeroornull.de.tuotuo.kotlin_jike.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking { 
    suspend fun getResult1(): String{
        delay(1000L)
        return "result1"
    }
    suspend fun getResult2(): String{
        delay(1000L)
        return "result2"
    }
    suspend fun getResult3(): String{
        delay(1000L)
        return "result3"
    }
//    val result = mutableListOf<String>()
    val results:List<String>
    
    val time = measureTimeMillis {
//        result.add(getResult1())
//        result.add(getResult2())
//        result.add(getResult3())
        val result1 = async { getResult1() }
        val result2 = async { getResult2() }
        val result3 = async { getResult3() }
        results = listOf(result1.await(),result2.await(),result3.await())
    }
    println("Time: $time")
    println(results)
    
    // 用旧知识学新知识
}