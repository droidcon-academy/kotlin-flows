package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.milliseconds

fun fetchDataWithTimeoutFallback(): Flow<String> = flow {
    delay(1500) // Simulate a slow network call
    emit("Data from API")
}

@OptIn(FlowPreview::class)
fun main(): Unit = runBlocking {
    fetchDataWithTimeoutFallback()
        .timeout(1000.milliseconds) // Set a timeout of 1 second
        .catch { e ->
            if (e is TimeoutCancellationException) {
                emit("Fallback data") // Emit fallback data on timeout
            }
        }
        .collect { data ->
            println("Received: $data")
        }
}