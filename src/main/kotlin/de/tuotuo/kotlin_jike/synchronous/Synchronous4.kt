package indi.zeroornull.de.tuotuo.kotlin_jike.synchronous

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    suspend fun prepare(){
        
    }
    var i = 0
    val lock = Any()

    val jobs = mutableListOf<Job>()

    repeat(10) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                synchronized(lock) {
//                    prepare()
                    i++
                }
            }
        }
        jobs.add(job)
    }
    jobs.joinAll()
    println("i = $i")
}