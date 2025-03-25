package dev.sunnat629.section5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val numbersFlow = flow {
        for (i in 1..3) {
            delay(500)
            emit(i)
        }
    }

    numbersFlow
        .onStart {
            println("Flow started")
        }
        .onEach { value ->
            println("Emitted: $value") // Use onEach to log every emission
        }
        .onCompletion { cause ->
            if (cause != null) {
                println("Flow completed with error: ${cause.message}")
            } else {
                println("Flow completed")
            }
        }
        .collect { value ->
            println("Received: $value")
        }
}