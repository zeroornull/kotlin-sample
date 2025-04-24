package indi.zeroornull.de.tuotuo.kotlin_jike.coroutine

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/*
fun main() = runBlocking {
    println("In runBlocking:${Thread.currentThread().name}")

    val deferred: Deferred<String> = async {
        println("In async:${Thread.currentThread().name}")
        delay(1000L)
        return@async "Task completed"
    }

    println("After async:${Thread.currentThread().name}")

    val result = deferred.await()
    println("Result is $result")

}*/


/*
deferred 不调用 仍然会执行
 */
fun main() = runBlocking {
    val deferred: Deferred<String> = async { 
        println("In async:${Thread.currentThread().name}") 
        delay(1000L)
        println("In async after delay")
        return@async "Task completed"
    }
    
    delay(2000L)
}