package com.sumin.coroutineflow.lesson16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

suspend fun main() {


    loadDataFlow()
        .map { State.Content(it) as State }
        .onStart { emit(State.Loading) }
        .catch {
            emit(State.Error)
        }
        .collect {
            when (it) {
                is State.Content -> {
                    println("Cpllected ${it.value}")
                }

                is State.Loading -> {
                    println("Loading...")
                }

                is State.Error -> {
                    println("Exception")
                }
            }
        }

}

fun loadDataFlow(): Flow<Int> = flow {
    repeat(5) {
        delay(500)
        emit(it)
    }
    throw RuntimeException()
}

sealed class State {

    data class Content(val value: Int) : State()

    object Loading : State()

    object Error : State()
}