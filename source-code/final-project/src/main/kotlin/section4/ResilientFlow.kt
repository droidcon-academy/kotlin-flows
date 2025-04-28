package section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException
import kotlin.time.Duration.Companion.milliseconds

fun main() = runBlocking {
    var attempt = 0
    val unstableFlow = flow {
        attempt++
        println("Starting attempt $attempt")
        when (attempt) {
            1, 2 -> {
                // Simulate a transient failure (e.g., network error)
                throw IOException("Network error on attempt $attempt")
            }
            else -> {
                // Simulate a successful result (with a slight delay)
                delay(100)  // simulate network latency
                emit("Successful result on attempt $attempt")
            }
        }
    }

    unstableFlow
        .timeout(200.milliseconds)   // cancel if an attempt takes more than 200ms
        .retry(2)                    // retry up to 2 times on any exception
        .catch { e ->
            // If all attempts failed or a non-retryable error occurred
            println("All attempts failed: ${e.message}. Emitting fallback data.")
            emit("Fallback Data")
        }
        .onCompletion { cause ->
            if (cause == null) {
                println("Flow completed successfully.")
            } else {
                println("Flow completed with error: $cause")
            }
        }
        .collect { value ->
            println("Collected value: $value")
        }
}