package section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

// Simulate API that sometimes delays or fails
fun fetchWeatherData(): Flow<String> = flow {
    val shouldFail = Random.nextBoolean()
    if (shouldFail) {
        delay(1500) // Deliberately too slow
        throw RuntimeException("Network error")
    } else {
        delay(300) // Fast enough
        emit("Weather: Sunny, 24°C")
    }
}

fun main() = runBlocking {
    fetchWeatherData()
        .timeout(1000.milliseconds) // Fail if not fast enough
        .retry(2) { cause ->
            println("Retrying... due to: ${cause.message}")
            cause is TimeoutCancellationException || cause is RuntimeException
        }
        .catch { e ->
            println("Handling failure: ${e.message}")
            emit("Fallback Weather: Data Unavailable")
        }
        .collect { weatherInfo ->
            println("Received: $weatherInfo")
        }
}