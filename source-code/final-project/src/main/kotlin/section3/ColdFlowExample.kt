package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun coldFlow(): Flow<Int> = flow {
    println("Starting cold flow")
    for (i in 1..3) {
        delay(500) // Simulate some work
        emit(i)   // Emit the value
    }
}

fun main() = runBlocking {
    coldFlow().collect { value ->
        println("Received: $value")
    }
}