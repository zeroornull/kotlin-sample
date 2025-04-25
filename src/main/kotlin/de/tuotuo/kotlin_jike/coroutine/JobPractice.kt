package indi.zeroornull.de.tuotuo.kotlin_jike.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
没有执行job2的原因是，它的launch中传入了job 作为coroutinecontext，
而它已经是complete 状态了，所以不会再执行job2的block 而是直接执行了job2的join ，然后结束。
 */
fun main() = runBlocking {
    val job = launch {
        logX("First coroutine start!")
        delay(1000L)
        logX("First coroutine end!")
    }

    job.join()
    val job2 = launch(job) {
        logX("Second coroutine start!")
        delay(1000L)
        logX("Second coroutine end!")
    }
    job2.join()
    logX("Process end!")
}