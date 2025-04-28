package section4

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    var attempt = 0
    // A flow that fails for the first two attempts, then succeeds
    val unstableFlow = flow {
        attempt++
        println("Attempt $attempt")
        if (attempt < 3) {
            // Fail on attempt 1 and 2
            throw RuntimeException("Error on attempt $attempt")
        }
        // Succeeds on attempt 3
        emit("Success on attempt $attempt")
    }

    unstableFlow
        .retry(2)  // retry up to 2 times on any exception
        .catch { e ->
            // This will run if all retries fail
            println("Caught failure after retries: ${e.message}")
        }
        .collect { value ->
            println("Collected $value")
        }
}