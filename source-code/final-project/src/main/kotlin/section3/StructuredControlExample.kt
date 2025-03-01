package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun structuredControlSimpleFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(500)
        emit(i)
    }
}

fun main() = runBlocking {
    structuredControlSimpleFlow()
        .onEach { println("Received: $it") }
        .launchIn(this) // Start collecting in a new coroutine

    delay(1500) // Keep the main coroutine alive
}