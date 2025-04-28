package section5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking

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
        .onCompletion { cause ->
            if (cause == null) {
                println("Flow completed normally!")
            } else {
                println("Flow ended with error: ${cause.message}")
            }
        }
        .collect { value ->
            println("Collected value: $value")
        }
}