package com.sumin.coroutineflow.lesson9

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

val coroutineScope = CoroutineScope(Dispatchers.IO)

suspend fun main() {
    val flow = MutableSharedFlow<Int>()

    coroutineScope.launch {
        repeat(100) {
            println("Emitted: $it")
            flow.emit(it)
            delay(1000)
        }
    }

    val job1 = coroutineScope.launch {
        flow.collect {
            println("First: $it")
        }
    }
    delay(5000)
    val job2 = coroutineScope.launch {
        flow.collect {
            println("Second: $it")
        }
    }
    job1.join()
    job2.join()
}

fun getFlow(): Flow<Int> = flow {
    repeat(100) {
        println("Emitted: $it")
        emit(it)
        delay(1000)
    }
}