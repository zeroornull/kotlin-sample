package indi.zeroornull.de.tuotuo.kotlin_jike.trycatch

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() = runBlocking {
    val parentJob = launch(fixedDispatcher) {

        launch(Job()) {
            var i = 0
            while (isActive) {
                Thread.sleep(500L)
                i++
                println("First i = $i")
            }
        }

        launch {
            var i = 0
            while (isActive) {
                Thread.sleep(500L)
                i++
                println("Second i = $i")
            }
        }

    }
    delay(2000L)
    parentJob.cancel()
    parentJob.join()
    println("end")
}