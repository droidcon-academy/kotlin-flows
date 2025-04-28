package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val numbersFlow = flow {
        for (i in 1..3) {
            delay(500) // Simulate work
            emit(i)    // Emit each number
        }
    }

    numbersFlow.collect { value ->
        println("Received: $value")
    }
}