package indi.zeroornull.de.tuotuo.kotlin_jike.trycatch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        var i = 0
        while (true) {
            Thread.sleep(500L)
            i++
            println("i = $i")
        }
    }
    delay(2000L)

    job.cancel()
    job.join()
    println("End")
}