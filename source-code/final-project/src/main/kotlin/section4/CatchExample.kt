package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchDataFromApi(): Flow<String> = flow {
    delay(500) // Simulate network call
    throw RuntimeException("API call failed") // Simulate an error
}

fun main() = runBlocking {
    fetchDataFromApi()
        .catch { e ->
            emit("Fallback data") // Emit fallback data on error
            println("Error: ${e.message}")
        }
        .collect { data ->
            println("Received: $data")
        }
}