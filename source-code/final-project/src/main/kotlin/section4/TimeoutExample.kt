@file:OptIn(FlowPreview::class)

package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.milliseconds

fun fetchDataWithTimeout(): Flow<String> = flow {
    delay(1500) // Simulate a slow network call
    emit("Data from API")
}

fun main() = runBlocking {
    try {
        fetchDataWithTimeout()
            .timeout(1000.milliseconds)// Set a timeout of 1 second
            .collect { data ->
                println("Received: $data")
            }
    } catch (e: TimeoutCancellationException) {
        println("Operation timed out: ${e.message}")
    }
}