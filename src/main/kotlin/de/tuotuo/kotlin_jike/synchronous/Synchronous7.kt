package indi.zeroornull.de.tuotuo.kotlin_jike.synchronous

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex

fun main() = runBlocking {
    val mutex = Mutex()

    var i = 0
    val jobs = mutableListOf<Job>()

    repeat(10) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                mutex.lock()
                i++
                mutex.unlock()
            }
        }
        jobs.add(job)
    }
    jobs.joinAll()
    println("i = $i")
}