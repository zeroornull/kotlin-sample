package indi.zeroornull.de.tuotuo.kotlin_jike.select

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/*
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val channel1 = produce {
        send(1)
        delay(200L)
        send(2)
        delay(200L)
        send(3)
        delay(150L)
    }

    val channel2 = produce {
        delay(100L)
        send("a")
        delay(200L)
        send("b")
        delay(200L)
        send("c")
    }

    channel1.consumeEach {
        println(it)
    }

    channel2.consumeEach {
        println(it)
    }

    println("Time cost :${System.currentTimeMillis() - startTime}")

}*/
/*@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val channel1 = produce {
        send("1")
        delay(200L)
        send("2")
        delay(200L)
        send("3")
        delay(150L)
    }

    val channel2 = produce {
        delay(100L)
        send("a")
        delay(200L)
        send("b")
        delay(200L)
        send("c")
    }

    suspend fun selectChannel(channel1: ReceiveChannel<String>, channel2: ReceiveChannel<String>) {
        select {
            channel1.onReceive {
                it.also { println(it) }
            }
            channel2.onReceive {
                it.also { println(it) }
            }
        }

    }

    repeat(6) {
        selectChannel(channel1, channel2)
    }

    println("Time cost :${System.currentTimeMillis() - startTime}")

}*/

/*
@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val channel1 = produce<String> {
*/
/*        send("1")
        delay(200L)
        send("2")
        delay(200L)
        send("3")
        delay(150L)*//*

        delay(15000L)
    }

    val channel2 = produce<String> {
        delay(100L)
        send("a")
        delay(200L)
        send("b")
        delay(200L)
        send("c")
    }

    suspend fun selectChannel(channel1: ReceiveChannel<String>, channel2: ReceiveChannel<String>) {
        select {
            channel1.onReceive {
                it.also { println(it) }
            }
            channel2.onReceive {
                it.also { println(it) }
            }
        }

    }

    repeat(6) {
        selectChannel(channel1, channel2)
    }

    println("Time cost :${System.currentTimeMillis() - startTime}")

}*/

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val channel1 = produce<String> {
/*        send("1")
        delay(200L)
        send("2")
        delay(200L)
        send("3")
        delay(150L)*/
        delay(15000L)
    }

    val channel2 = produce<String> {
        delay(100L)
        send("a")
        delay(200L)
        send("b")
        delay(200L)
        send("c")
    }

    suspend fun selectChannel(channel1: ReceiveChannel<String>, channel2: ReceiveChannel<String>) {
        select<String> {
            channel1.onReceiveCatching {
                it.getOrNull()?:"channel1 is closed!"
            }
            channel2.onReceiveCatching {
                it.getOrNull()?:"channel2 is closed!"
            }
        }

    }

    repeat(6) {
        val result = selectChannel(channel1, channel2)
        println(result)
    }

    channel1.cancel()
    channel1.cancel()
    println("Time cost :${System.currentTimeMillis() - startTime}")

}
