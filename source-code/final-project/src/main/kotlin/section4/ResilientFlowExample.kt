package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchWeatherData(): Flow<String> = flow {
    delay(500) // Simulate network call
    if (Math.random() < 0.5) {
        throw RuntimeException("API call failed") // Simulate random failures
    }
    emit("Sunny, 25°C")
}

fun main() = runBlocking {
    fetchWeatherData()
        .retry(3) { e ->
            println("Retrying... Error: ${e.message}")
            delay(1000) // Delay before retrying
            true // Retry on any error
        }
        .catch { e ->
            emit("Fallback weather data") // Emit fallback data on error
        }
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