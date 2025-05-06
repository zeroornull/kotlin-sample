package indi.zeroornull.de.tuotuo.kotlin_jike.flow

import indi.zeroornull.de.tuotuo.kotlin_jike.coroutine.logX
import indi.zeroornull.de.tuotuo.kotlin_jike.coroutine.mySingleDispatcher
import indi.zeroornull.org.kotlinlang.play.introduction.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/*
fun main()= runBlocking {
    flow{
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }.filter{it > 2}
        .map { it * 2 }
        .take(2)
        .collect { println(it) }
}*/

/*
fun main() = runBlocking {
    flowOf(1, 2, 3, 4, 5).filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .collect { println(it) }
    listOf(1, 2, 3, 4, 5).filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .forEach { println(it) }
}*/

/*
fun main() = runBlocking {
    flowOf(1, 2, 3, 4, 5).toList().filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .forEach { println(it) }
    listOf(1, 2, 3, 4, 5).asFlow().filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .collect { println(it) }
}*/

/*fun main() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        .filter {
            println("filter: $it")
            it > 2
        }
        .map {
            println("map: $it")
            it * 2
        }
        .take(2)
        .onStart { println("onStart") }
        .collect { println("collect: $it") }
}*/

/*fun main() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        .take(2)
        .filter {
            println("filter: $it")
            it > 2
        }
        .map {
            println("map: $it")
            it * 2
        }

        .onStart { println("onStart") }
        .collect { println("collect: $it") }
}*/

/*
fun main() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        .onCompletion { 
            println("onCompletion")
        }
        .filter {
            println("filter: $it")
            it > 2
        }
        .map {
            println("map: $it")
            it * 2
        }
        .take(2)
        .collect { println("collect: $it") }
}*/

/*
fun main() = runBlocking {
    launch {
        flow {
            emit(1)
            emit(2)
            emit(3)
        }.onCompletion { println("onCompletion first: $it") }
            .collect {
                println("collect: $it")
                if (it == 2) {
                    cancel()
                    println("cancel")
                }
            }
    }
    delay(100L)
    flowOf(4, 5, 6)
        .onCompletion {
            println("onCompletion second: $it")
        }.collect {
            println("collect: $it")
            throw IllegalStateException()
        }
}*/

/*
fun main() = runBlocking {
    val flow = flow {
        emit(1)
        emit(2)
        throw IllegalStateException()
        emit(3)
    }

    flow.map { it * 2 }
        .catch { println("  catch $it") }
        .collect { println(it) }
}*/

/*
fun main() = runBlocking {
    val flow = flow {
        emit(1)
        emit(2)
        emit(3)
    }

    flow.map { it * 2 }
        .catch { println("  catch $it") }
        .filter { it/0 > 1 }
        .collect { println(it) }
}*/

/*
fun main() = runBlocking {
    flowOf(4, 5, 6)
        .onCompletion { println("onCompletion second: $it") }
        .collect {
            try {
                println("collect: $it")
                throw IllegalStateException()
            } catch (e: Exception) {
                println("collect: $e")
            }
        }
}*/

/*
fun main() = runBlocking {
    val flow = flow {
        logX("Start")
        emit(1)
        logX("Emit: 1")
        emit(2)
        logX("Emit: 2")
        emit(3)
        logX("Emit: 3")
    }
    flow.filter {
        logX("Filter: $it")
        it > 2
    }.flowOn(Dispatchers.IO)
        .collect {
            logX("Collect: $it")
        }
}*/
/*

fun main() = runBlocking {
    val flow = flow {
        logX("Start")
        emit(1)
        logX("Emit: 1")
        emit(2)
        logX("Emit: 2")
        emit(3)
        logX("Emit: 3")
    }
    
    flow.flowOn(Dispatchers.IO)
        .filter {
        logX("Filter: $it")
        it > 2
    }
        .collect {
            withContext(mySingleDispatcher){
                logX("Collect: $it")
            }
        }
}*/


/*
fun main() = runBlocking {
    val flow = flow {
        logX("Start")
        emit(1)
        logX("Emit: 1")
        emit(2)
        logX("Emit: 2")
        emit(3)
        logX("Emit: 3")
    }

    withContext(mySingleDispatcher) {
        flow.flowOn(Dispatchers.IO).filter {
            logX("Filter: $it")
            it > 2
        }.collect {
            logX("Collect $it")
        }
    }
}*/

/*
fun main(): Unit = runBlocking {
    val flow = flow {
        logX("Start")
        emit(1)
        logX("Emit: 1")
        emit(2)
        logX("Emit: 2")
        emit(3)
        logX("Emit: 3")
    }
    val scope = CoroutineScope(mySingleDispatcher)
    flow.flowOn(Dispatchers.IO).filter {
        logX("Filter: $it")
        it > 2
    }.onEach {
        logX("onEach $it")
    }.launchIn(scope)
}*/

/*
fun main() = runBlocking {
    val scope = CoroutineScope(mySingleDispatcher)
    val flow = flow {
        logX("Start")
        emit(1)
        logX("Emit: 1")
        emit(2)
        logX("Emit: 2")
        emit(3)
        logX("Emit: 3")
    }
        .flowOn(Dispatchers.IO)
        .filter {
            logX("Filter: $it")
            it > 2
        }
        .onEach {
            logX("onEach $it")
        }

    scope.launch {
        flow.collect()
    }

    delay(100L)
}*/


/*
fun main() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        .toList()
        .filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .forEach { println(it) }
}*/

/*
fun main() = runBlocking {
    var flow = flow {
        (1..3).forEach {
            println("Before send $it")
            emit(it)
            println("send $it")
        }
    }

    val channel = produce<Int>(capacity = 0) {
        (1..3).forEach {
            println("Before send $it")
            send(it)
            println("send $it")
        }
    }

    println("end")
}*/

/*
fun main() = runBlocking {
    flow {
        println("emit: 3")
        emit(3)
        println("emit: 4")
        emit(4)
        println("emit: 5")
        emit(5)
    }.filter {
        println("filter: $it")
        it > 2
    }.map {
        println("map: $it")
        it * 2
    }.collect {
        println("collect: $it")
    }
}*/

/*
fun main() = runBlocking {
    fun loadData() = flow {
        repeat(3) {
            delay(100L)
            emit(it)
            logX("emit $it")
        }
    }

    val uiScope = CoroutineScope(mySingleDispatcher)

    loadData()
        .map { it * 2 }
        .flowOn(Dispatchers.IO)
        .onEach {
            logX("onEach $it")
        }
        .launchIn(uiScope)
    delay(1000L)
}*/

fun main() = runBlocking {
    fun loadData() = flow {
        repeat(3) {
            delay(100L)
            emit(it)
            logX("emit $it")
        }
    }

    fun updateUI(it: Int) {

    }

    fun showLoading() {
        println("Show loading")
    }

    fun hideLoading() {
        println("Hide loading")
    }

    val uiScope = CoroutineScope(mySingleDispatcher)

    loadData()
        .onStart { showLoading() }
        .map { it * 2 }
        .flowOn(Dispatchers.IO)
        .catch { throwable ->
            println(throwable)
            hideLoading()
            emit(-1)
        }
        .onEach {
            updateUI(it)
        }
        .onEach { updateUI(it) }
        .onCompletion { hideLoading() }
        .launchIn(uiScope)
    delay(1000L)
}