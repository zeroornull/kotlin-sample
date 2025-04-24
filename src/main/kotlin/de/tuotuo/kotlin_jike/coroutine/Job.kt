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

    val deferred = async {
        logX("Coroutine start!")
        delay(1000L)
        logX("Coroutine end!")
        "Coroutine result"
    }
    val result = deferred.await()
    println("Result is $result")
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


