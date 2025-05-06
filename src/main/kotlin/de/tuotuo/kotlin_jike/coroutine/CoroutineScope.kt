package indi.zeroornull.de.tuotuo.kotlin_jike.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Unconfined

/*
fun main() = runBlocking(Unconfined) {
    val scope = CoroutineScope(Job())
    scope.launch {
        logX("First start")
        delay(1000L)
        logX("First End")
    }
    scope.launch {
        logX("Second start")
        delay(1000L)
        logX("First End")
    }
    scope.launch {
        logX("Third start")
        delay(1000L)
        logX("Third End")
    }

    delay(500L)
    scope.cancel()
    delay(1000L)
}*/

/*
@OptIn(ExperimentalStdlibApi::class)
fun main()=  runBlocking {
    val scope = CoroutineScope(Job() + mySingleDispatcher)
    
    scope.launch { 
        //
        logX(coroutineContext[CoroutineDispatcher] == mySingleDispatcher)
        delay(1000L)
        logX("first end!")
    }
    delay(500L)
    scope.cancel()
    delay(1000L)
}*/

/*
@OptIn(ExperimentalStdlibApi::class)
fun main() = runBlocking {
    val scope = CoroutineScope(Job() + mySingleDispatcher)
    scope.launch(CoroutineName("MyFirstCoroutine!")) {
        logX(coroutineContext[CoroutineDispatcher] == mySingleDispatcher)
        delay(1000L)
        logX("First End!")
    }
    delay(500L)
    scope.cancel()
    delay(1000L)

}*/

suspend fun main() {
    val myExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("CoroutineExceptionHandler got $throwable")
    }
    val scope = CoroutineScope(Job() + mySingleDispatcher)

    val job = scope.launch(myExceptionHandler) {
        val s: String? = null
        s!!.length
    }
    job.join()
}
