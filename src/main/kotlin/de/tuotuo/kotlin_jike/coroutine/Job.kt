package indi.zeroornull.de.tuotuo.kotlin_jike.coroutine

import indi.zeroornull.de.tuotuo.kotlin_jike.calculate.start
import indi.zeroornull.org.kotlinlang.play.introduction.log
import kotlinx.coroutines.*
import kotlin.random.Random

fun main() = runBlocking {
/*    val job = launch {
        delay(1000L)
    }
    job.log()
    job.cancel()
    job.log()
    delay(1500L)*/

    //
    //
    /*    val job = launch(start= CoroutineStart.LAZY) {
            logX("Coroutine Start")
            delay(1000L)
        }
        delay(500L)
        job.log()
        job.start()
        job.log()
        delay(500L)
        job.cancel()
        delay(500L)
        job.log()
        delay(200L)
        logX("Process End")*/

    /*    val job = launch(start = CoroutineStart.LAZY) {
            logX("Coroutine start")
            delay(1000L)
        }
        delay(500L)
        job.log()
        job.start()
        job.log()
        delay(1100L)
        job.log()
        delay(2000L)
        logX("Process end!")*/

    /*    val job = launch(start=CoroutineStart.LAZY) {
            logX("Coroutine start")
            delay(4000L)
        }
        delay(500L)
        job.log()
        job.start()
        job.log()
        delay(1100L)
        job.log()
        delay(2000L)
        logX("Process end!")*/

    /*    suspend fun download() {
            //
            val time = (Random.nextDouble() * 1000).toLong()
            logX("Delay time:=$time")
            delay(time)
        }
    
        val job = launch(start = CoroutineStart.LAZY) {
            logX("Coroutine start")
            download()
            logX("Coroutine end")
        }
        delay(500L)
        job.log()
        job.start()
        job.log()
        job.invokeOnCompletion {
            job.log()
        }
        
        job.join()
        logX("Coroutine end")*/

    /*    val deferred = async {
            logX("Coroutine start!")
            delay(1000L)
            logX("Coroutine end!")
            "Coroutine result"
        }
        val result = deferred.await()
        println("Result is $result")
        logX("Process end!")*/

    val parentJob: Job
    var job1: Job? = null
    var job2: Job? = null
    var job3: Job? = null

    parentJob = launch {
        job1 = launch {
            logX("Job1 start")
            delay(1000L)
            logX("Job1 Done")
        }

        job2 = launch {
            logX("Job2 start")
            delay(3000L)
            logX("Job2 Done")
        }

        job3 = launch {
            logX("Job3 start")
            delay(5000L)
            logX("Job3 Done")
        }
    }
    delay(500L)
    parentJob.children.forEachIndexed { index, job ->
        when (index) {
            0 -> println("job1==job is ${job1 == job}")
            1 -> println("job1==job is ${job2 == job}")
            2 -> println("job1==job is ${job3 == job}")
        }
    }
//    parentJob.join()
    parentJob.cancel()
    logX("Process end!")


}

fun Job.log() {
    logX(
        """
        isActive = $isActive
        isCancelled = $isCancelled
        isCompleted = $isCompleted
    """.trimIndent()
    )
}

fun logX(any: Any?) {
    println(
        """
        ===================================
        $any
        Thread:${Thread.currentThread().name}
        ===================================
    """.trimIndent()
    )
}


