package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val numbersFlow = flow {
        for (i in 1..3) {
            delay(500) // Simulate a slow producer
            emit(i)
        }
    }

    numbersFlow
        .onStart {
            println("Flow is starting! Preparing to emit values...")
        }
        .onEach { value ->
            println("Emitted value: $value")
        }
        .collect { value ->
            println("Collected value: $value")
        }
}