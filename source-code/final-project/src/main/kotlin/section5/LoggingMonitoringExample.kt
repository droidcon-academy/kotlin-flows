package dev.sunnat629.section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchData(): Flow<String> = flow {
    delay(500) // Simulate network request
    emit("Data from API")
}

fun main() = runBlocking {
    fetchData()
        .onStart {
            println("Flow started at ${System.currentTimeMillis()}")
        }
        .onCompletion {
            println("Flow completed at ${System.currentTimeMillis()}")
        }
        .catch { e ->
            println("Flow failed with error: ${e.message}")
        }
        .collect { data ->
            println("Received: $data")
        }
}