package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchData(): Flow<String> = flow {
    delay(500) // Simulate network call
    emit("Data from API")
}

fun main() = runBlocking {
    fetchData()
        .onCompletion { cause ->
            if (cause != null) {
                println("Flow completed with error: ${cause.message}")
            } else {
                println("Flow completed successfully")
            }
        }
        .collect { data ->
            println("Received: $data")
        }
}