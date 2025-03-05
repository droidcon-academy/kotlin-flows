package dev.sunnat629.section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main(): Unit = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>()

    // Emitter
    launch {
        for (i in 1..5) {
            delay(500)
            println("Emitting: $i")
            sharedFlow.emit(i)
        }
    }

    // Collector 1
    launch {
        sharedFlow.collect { value ->
            println("Collector 1 received: $value")
        }
    }

    // Collector 2 (joins late)
    delay(1000)
    launch {
        sharedFlow.collect { value ->
            println("Collector 2 received: $value")
        }
    }
}