package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val numbersFlow = flow {
        for (i in 1..3) {
            delay(500)
            if (i == 2) throw RuntimeException("Error on $i")
            emit(i)
        }
    }

    numbersFlow
        .catch { e ->
            println("Caught exception: ${e.message}")
            emit(-1) // Emit a fallback value
        }
        .collect { value ->
            println("Received: $value")
        }
}