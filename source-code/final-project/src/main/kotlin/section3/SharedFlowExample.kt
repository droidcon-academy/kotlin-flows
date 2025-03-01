package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main(): Unit = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>()

    // Start emitting values
    launch {
        for (i in 1..5) {
            delay(500)
            sharedFlow.emit(i)
        }
    }

    // First collector
    launch {
        sharedFlow.collect { value ->
            println("Collector 1: $value")
        }
    }

    // Second collector (joins late)
    delay(1000)
    launch {
        sharedFlow.collect { value ->
            println("Collector 2: $value")
        }
    }
}