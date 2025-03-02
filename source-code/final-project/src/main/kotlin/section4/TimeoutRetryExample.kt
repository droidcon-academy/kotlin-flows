@file:OptIn(FlowPreview::class)

package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.milliseconds

fun fetchDataWithTimeoutAndRetry(): Flow<String> = flow {
    delay(1500) // Simulate a slow network call
    emit("Data from API")
}

fun main() = runBlocking {
    fetchDataWithTimeoutAndRetry()
        .timeout(1000.milliseconds) // Set a timeout of 1 second
        .retry(3) { e ->
            if (e is TimeoutCancellationException) {
                println("Retrying due to timeout...")
                delay(500) // Delay before retrying
                true // Retry on timeout
            } else {
                false // Stop retrying for other errors
            }
        }
        .catch { e ->
            emit("Fallback data") // Emit fallback data after retries fail
        }
        .collect { data ->
            println("Received: $data")
        }
}