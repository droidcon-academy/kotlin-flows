package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val fastFlow = flow {
        for (i in 1..10) {
            delay(100)
            emit(i)
        }
    }

    fastFlow
        .conflate() // Skip outdated values
        .collect { value ->
            delay(300) // Slow collector
            println("Received: $value")
        }
}
