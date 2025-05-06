package indi.zeroornull.de.tuotuo.kotlin_jike.synchronous

import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

fun main() = runBlocking {
    val mySingleDispatcher = Executors.newSingleThreadExecutor{
        Thread(it, "MySingleThread").apply { isDaemon = true }
    }.asCoroutineDispatcher()   
    
    var i = 0
    val jobs = mutableListOf<Job>()
    repeat(10){
        val job = launch(mySingleDispatcher){
            repeat(1000){
                i++
            }
        }
        jobs.add(job)
    }
    
    jobs.joinAll()
    println("i = $i")
}