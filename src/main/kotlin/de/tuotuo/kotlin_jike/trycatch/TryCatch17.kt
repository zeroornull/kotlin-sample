package indi.zeroornull.de.tuotuo.kotlin_jike.trycatch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

fun main() = runBlocking {
    val scope = CoroutineScope(coroutineContext)

    scope.launch { 
        async { 
            delay(1000)
        }
        launch {
            delay(1000)

            launch {
                delay(1000)
                1/0
            }
        }
        delay(100L)
    }
    
    delay(1000L)
    println("End")
}