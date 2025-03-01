package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simpleFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(500)
        emit(i)
    }
}

fun main() = runBlocking {
    val job = launch {
        simpleFlow().collect { value ->
            println("Received: $value")
        }
    }

    delay(1000)
    job.cancel() // Cancel the collector
}