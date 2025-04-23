package indi.zeroornull.de.tuotuo.kotlin_jike.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

/*
fun main() {
    val list = getList()
    printList(list)
}

fun getList(): List<Int> {
    val list = mutableListOf<Int>()
    println("Add 1")
    list.add(1)
    println("Add 2")
    list.add(2)
    println("Add 3")
    list.add(3)
    println("Add 4")
    list.add(4)
    return list
}

fun printList(list: List<Int>) {
    val i = list[0]
    println("Get$i")
    val j = list[1]
    println("Get$j")
    val k = list[2]
    println("Get$k")
    val m = list[3]
    println("Get$m")
}*/

/*
fun main() {
    val sequence = getSequence()
    printSequence(sequence)
}

fun getSequence() = sequence {
    println("Add 1")
    yield(1)
    println("Add 2")
    yield(2)
    println("Add 3")
    yield(3)
    println("Add 4")
    yield(4)
}

fun printSequence(sequence: Sequence<Int>) {
    val iterator = sequence.iterator()
    val i = iterator.next()
    println("Get$i")
    val j = iterator.next()
    println("Get$j")
    val k = iterator.next()
    println("Get$k")
    val m = iterator.next()
    println("Get$m")
}*/

/*fun main() {
    println(Thread.currentThread().name)
    thread {
        println(Thread.currentThread().name)
        Thread.sleep(100)
    }
    Thread.sleep(1000L)
}*/

/*
这里要配置特殊的VM参数：-Dkotlinx.coroutines.debug
这样一来，Thread.currentThread().name就能会包含：协程的名字@coroutine#1
 */

/*fun main() = runBlocking {
*//*    val channel = getProducer(this)
    testConsumer(channel)*//*

    println(Thread.currentThread().name)

    launch {
        println(Thread.currentThread().name)
        delay(100L)
    }
    Thread.sleep(1000L)
}*/

/*fun main() {
    repeat(1000_000_000) {
        thread {
            Thread.sleep(1000000)
        }
    }
    Thread.sleep(10000L)
}*/

/*fun main() = runBlocking {
    repeat(1000_000_000) {
        launch {
            delay(1000000)
        }
    }
    delay(10000L)
}*/

/*fun main() = runBlocking(Dispatchers.IO) {
    repeat(3) {
        launch {
            repeat(3) {
                println(Thread.currentThread().name)
                delay(100)
            }
        }
    }
    delay(5000L)
}*/

/*fun main() = runBlocking {
    launch {
        repeat(3) {
            delay(1000)
            println("Print-1:${Thread.currentThread().name}")
        }
    }

    launch {
        repeat(3) {
            delay(900L)
            println("Print-2:${Thread.currentThread().name}")
        }
    }
    delay(3000L)
}*/

fun main() = runBlocking {
    launch {
        repeat(3) {
            Thread.sleep(1000L)
            println("Print-1:${Thread.currentThread().name}")
        }
    }

    launch {
        repeat(3) {
            Thread.sleep(900L)
            println("Print-2:${Thread.currentThread().name}")
        }
    }
    delay(3000L)
}


/*fun main() {
    repeat(3) {
        Thread.sleep(1000L)
        println("Print-1:${Thread.currentThread().name}")
    }

    repeat(3) {
        Thread.sleep(900L)
        println("Print-2:${Thread.currentThread().name}")
    }
}*/


fun getProducer(scope: CoroutineScope) = scope.produce {
    println("Send:1")
    send(1)
    println("Send:2")
    send(2)
    println("Send:3")
    send(3)
    println("Send:4")
    send(4)
}

suspend fun testConsumer(channel: ReceiveChannel<Int>) {
    delay(100)
    val i = channel.receive()
    println("Receive $i")
    delay(100)
    val j = channel.receive()
    println("Receive $j")
    delay(100)
    val k = channel.receive()
    println("Receive $k")
    delay(100)
    val m = channel.receive()
    println("Receive $m")
}

