package indi.zeroornull.de.tuotuo.kotlin_jike.synchronous

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    var i = 0

    launch(Dispatchers.Default) {
        repeat(1000) {
            i++
        }
    }
    delay(1000L)
    println("i = $i")
}