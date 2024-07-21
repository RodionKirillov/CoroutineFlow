package com.sumin.coroutineflow.crypto_app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    val state: Flow<State> = repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(currencyList = it) as State }
        .onStart {
            log("onStart")
            emit(State.Loading)
        }
        .onEach { log("onEach") }
        .onCompletion {
            log("onCompletion")
        }


    private fun log(message: String) {
        Log.d(LOG_TAG, message)
    }

    companion object {

        private const val LOG_TAG = "LOG_TAG"
    }
}
