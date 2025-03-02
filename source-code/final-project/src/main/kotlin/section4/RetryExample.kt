package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchDataWithRetry(): Flow<String> = flow {
    delay(500) // Simulate network call
    throw RuntimeException("API call failed") // Simulate an error
}

fun main() = runBlocking {
    fetchDataWithRetry()
        .retry(3) { e ->
            println("Retrying... Error: ${e.message}")
            delay(1000) // Delay before retrying
            true // Retry on any error
        }
        .catch { e ->
            emit("Fallback data") // Emit fallback data after retries fail
        }
        .collect { data ->
            println("Received: $data")
        }
}