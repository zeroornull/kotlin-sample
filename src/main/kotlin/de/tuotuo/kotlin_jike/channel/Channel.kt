package indi.zeroornull.de.tuotuo.kotlin_jike.channel

import indi.zeroornull.de.tuotuo.kotlin_jike.coroutine.logX
import indi.zeroornull.org.kotlinlang.play.introduction.log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
fun main() = runBlocking {

    val channel = Channel<Int>()
    launch {
        (1..3).forEach {
            channel.send(it)
            logX("Send:$it")
        }
        channel.close()
    }

    launch {
        for (i in channel) {
            logX("Receive $i")
        }
    }

    logX("end")

}*/


/*
fun main() = runBlocking {

    val channel = Channel<Int>(capacity = Channel.Factory.UNLIMITED)
    launch {
        (1..3).forEach {
            channel.send(it)
            logX("Send:$it")
        }
        channel.close()
    }

    launch {
        for (i in channel) {
            logX("Receive $i")
        }
    }

    logX("end")

}*/

/*
fun main() = runBlocking {

    val channel = Channel<Int>(capacity = Channel.Factory.CONFLATED)
    launch {
        (1..3).forEach {
            channel.send(it)
            logX("Send:$it")
        }
        channel.close()
    }

    launch {
        for (i in channel) {
            logX("Receive $i")
        }
    }

    logX("end")

}*/

/*
fun main() = runBlocking {

    val channel = Channel<Int>(capacity = 1,onBufferOverflow = BufferOverflow.DROP_OLDEST)
    launch {
        (1..3).forEach {
            channel.send(it)
            logX("Send:$it")
        }
        channel.close()
    }

    launch {
        for (i in channel) {
            logX("Receive $i")
        }
    }

    logX("end")

}*/

/*
fun main() = runBlocking {

    val channel = Channel<Int>(capacity = 3,onBufferOverflow = BufferOverflow.DROP_OLDEST)
    launch {
        (1..3).forEach {
            channel.send(it)
            logX("Send:$it")
        }
        channel.close()
    }

    launch {
        for (i in channel) {
            logX("Receive $i")
        }
    }

    logX("end")

}*/

/*
fun main() = runBlocking {

    val channel = Channel<Int>(capacity = 3,onBufferOverflow = BufferOverflow.DROP_LATEST)
    launch {
        (1..3).forEach {
            channel.send(it)
            logX("Send:$it")
        }
        channel.send(4)
        println("Send: 4")
        channel.send(5)
        println("Send: 5")
        channel.close()
    }

    launch {
        for (i in channel) {
            logX("Receive $i")
        }
    }

    logX("end")

}*/


/*
fun main() = runBlocking {

    val channel = Channel<Int>(Channel.UNLIMITED){
        println("onUndeliveredElement=$it")
    }
    (1..3).forEach { 
        channel.send(it)
    }
    
    channel.receive()
    channel.cancel()

}*/

/*
@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    val channel: ReceiveChannel<Int> = produce {
        (1..3).forEach {
            send(it)
            logX("Send: $it")
        }
    }

    launch {
        for (i in channel) {
            logX("Receive: $i")
        }
    }
    logX("end")

}
*/

/*@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    val channel: ReceiveChannel<Int> = produce {
        (1..3).forEach {
            send(it)
            logX("Send: $it")
        }
    }

    channel.receive()
    channel.receive()
    channel.receive()
    channel.receive()
    logX("end")

}*/

/*
@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    val channel: Channel<Int> = Channel()

    launch {
        (1..3).forEach {
            channel.send(it)
        }
    }
    channel.receive()
    println("Receive: 1")
    channel.receive()
    println("Receive: 2")
    channel.receive()
    println("Receive: 3")
    channel.receive()
    logX("end")
}*/

/*
@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    val channel: ReceiveChannel<Int> = produce {
        (1..3).forEach {
            send(it)
            logX("Send: $it")
        }
    }

    while (!channel.isClosedForReceive) {
        val i = channel.receive()
        println("Received: $i")
    }
    println("end")
}*/

/*
@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    val channel: ReceiveChannel<Int> = produce(capacity = 3) {
        (1..300).forEach {
            send(it)
            logX("Send: $it")
        }
    }

    while (!channel.isClosedForReceive) {
        val i = channel.receive()
        println("Received: $i")
    }
    logX("end")
}*/

/*
@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    val channel: ReceiveChannel<Int> = produce {
        (1..300).forEach {
            send(it)
            logX("Send: $it")
        }
    }

    channel.consumeEach { 
        println("Received: $it")
    }
    println("end")
}*/

/*@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    val channel = produce<Int>(capacity = 10) {
        (1..3).forEach {
            send(it)
            println("send $it")
        }
    }

    println("end")
}*/

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    val channel = produce<Int>(capacity = 0) {
        (1..3).forEach {
            println("Before sending $it")
            send(it)
            println("send $it")
        }
    }

    println("end")
}