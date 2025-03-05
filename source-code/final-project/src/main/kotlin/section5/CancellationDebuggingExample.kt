package dev.sunnat629.section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchCancellationData(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(500)
        println("Emitting: $i")
        emit(i)
    }
}

fun main(): Unit = runBlocking {
    val job = launch {
        fetchCancellationData()
            .onCompletion { println("Flow completed") }
            .collect { value ->
                println("Received: $value")
            }
    }

    delay(1500)
    job.cancel() // Cancel the collector
    println("Collector canceled")
}