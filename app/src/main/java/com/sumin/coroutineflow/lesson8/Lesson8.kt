package com.sumin.coroutineflow.lesson8

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun main() {
    val flow = getFlow()
    flow.collect {
        println(it)
    }

}

fun getFlow(): Flow<Int> = flow {
    repeat(100) {
        println("Emitted: $it")
        emit(it)
        delay(1000)
    }
}