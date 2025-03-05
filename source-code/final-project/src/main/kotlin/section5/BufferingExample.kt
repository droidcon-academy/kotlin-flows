package dev.sunnat629.section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val fastFlow = flow {
        for (i in 1..5) {
            delay(100) // Fast emission
            emit(i)
        }
    }

    fastFlow
        .buffer(10) // Buffer up to 10 values
        .collect { value ->
            delay(300) // Slow collection
            println("Received: $value")
        }
}