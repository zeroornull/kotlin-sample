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
                mutex.withLock {
                    i++
                }
            }
        }
        jobs.add(job)
    }
    jobs.joinAll()
    println("i = $i")
}

public suspend inline fun <T> Mutex.withLock(owner: Any? = null, action: () -> T): T {
    lock(owner)
    try {
        return action()
    } finally {
        unlock(owner)
    }
}