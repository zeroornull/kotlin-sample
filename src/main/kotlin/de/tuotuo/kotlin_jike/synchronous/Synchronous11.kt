package indi.zeroornull.de.tuotuo.kotlin_jike.synchronous

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

sealed class Msg
object AddMsg : Msg()
class ResultMsg(
    val result: CompletableDeferred<Int>
) : Msg()

fun main() = runBlocking {
    suspend fun addActor() = actor<Msg> {
        var counter = 0
        for (msg in channel) {
            when (msg) {
                is AddMsg -> counter++
                is ResultMsg -> msg.result.complete(counter)
            }
        }
    }

    val actor = addActor()
    val jobs = mutableListOf<Job>()

    repeat(10) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                actor.send(AddMsg)
            }
        }
        jobs.add(job)
    }
    jobs.joinAll()

    val deferred = CompletableDeferred<Int>()
    actor.send(ResultMsg(deferred))

    val result = deferred.await()
    actor.close()

    println("i = $result")

}

