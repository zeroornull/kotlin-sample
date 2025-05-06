package indi.zeroornull.de.tuotuo.kotlin_jike.trycatch

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() = runBlocking {
    val parentJob = launch(Dispatchers.Default) {

        launch {
            var i = 0
            while (true) {
                try {
                    delay(500L)
                }catch (e: CancellationException){
                    println("Catch CancellationException")
                    throw e
                }
                
                i++
                println("First i = $i")
            }
        }

        launch {
            var i = 0
            while (true) {
                delay(500L)
                i++
                println("First i = $i")
            }
        }

    }
    delay(2000L)
    parentJob.cancel()
    parentJob.join()
    println("end")
}