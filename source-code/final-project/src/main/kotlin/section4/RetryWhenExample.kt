package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchDataWithConditionalRetry(): Flow<String> = flow {
    delay(500) // Simulate network call
    throw RuntimeException("Network error") // Simulate a network error
}

fun main() = runBlocking {
    fetchDataWithConditionalRetry()
        .retryWhen { cause, attempt ->
            if (cause is RuntimeException && attempt < 3) {
                println("Retrying due to network error... Attempt: $attempt")
                delay(1000) // Delay before retrying
                true // Retry on network errors
            } else {
                false // Stop retrying
            }
        }
        .catch { e ->
            emit("Fallback data") // Emit fallback data after retries fail
        }
        .collect { data ->
            println("Received: $data")
        }
}