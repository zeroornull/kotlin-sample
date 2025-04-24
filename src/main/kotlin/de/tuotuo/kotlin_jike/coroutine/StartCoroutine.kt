package indi.zeroornull.de.tuotuo.kotlin_jike.coroutine

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/*
@OptIn(DelicateCoroutinesApi::class)
fun main() {
    GlobalScope.launch(Dispatchers.IO) {
        println("Coroutine started: ${Thread.currentThread().name}")
        delay(1000L)
        println("Hello World!")
    }

    println("After launch:${Thread.currentThread().name}")
    Thread.sleep(2000L)
}*/


/*fun main() {
    GlobalScope.launch {
        println("Coroutine started")
        delay(1000L)
        println("Hello World!")
    }
    println("after launch")
//    Thread.sleep(2000L)
    println("Process End!!")
}*/

/*fun main() {
    runBlocking {
        println("Coroutine started")
        delay(1000L)
        println("Hello World!")
    }
    println("after launch")
    Thread.sleep(2000L)
    println("Process End!!")
}*/

/*fun main() {
    runBlocking { 
        println("First:${Thread.currentThread().name}")
        delay(1000L)
        println("Hello First!")
    }

    runBlocking {
        println("Second:${Thread.currentThread().name}")
        delay(1000L)
        println("Hello Second!")
    }

    runBlocking {
        println("Third:${Thread.currentThread().name}")
        delay(1000L)
        println("Hello Third!")
    }
    
}*/

fun main() {
    val result = runBlocking { 
        delay(1000L)
        return@runBlocking "Coroutine done!"
    }
    
    println("Result is $result")
}


/*
fun main() {
    thread(isDaemon = true){
        Thread.sleep(1000L)
        println("Hello World!")
    }
}*/
