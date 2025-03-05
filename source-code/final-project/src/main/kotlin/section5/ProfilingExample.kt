package dev.sunnat629.section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun profilingFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(500) // Simulate heavy computation
        emit(i)
    }
}

fun main(): Unit = runBlocking {
    profilingFlow()
        .flowOn(Dispatchers.Default) // Run on a background thread
        .collect { value ->
            println("Received: $value")
        }
}