package dev.sunnat629.section2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun simpleFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(500) // Simulate some work
        emit(i)   // Emit the value
    }
}

fun main() = runBlocking {
    simpleFlow().collect { value ->
        println("Received: $value")
    }
}
