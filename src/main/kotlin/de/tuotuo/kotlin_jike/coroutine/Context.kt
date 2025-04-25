package indi.zeroornull.de.tuotuo.kotlin_jike.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/*
suspend fun main()  {
    val user = getUserInfo()
    logX(user)
}
*/

val mySingleDispatcher = Executors.newSingleThreadExecutor {
    Thread(it, "MySingleDispatcher").apply { isDaemon = true }
}.asCoroutineDispatcher()


fun main() = runBlocking(Unconfined) {
    val user = getUserInfo()
    logX(user)
}


suspend fun getUserInfo(): String {
    logX("Before IO context")
    withContext(Dispatchers.IO) {
        logX("In IO Context.")
        delay(1000L)
    }
    logX("After IO Context.")
    return "xxx"
}