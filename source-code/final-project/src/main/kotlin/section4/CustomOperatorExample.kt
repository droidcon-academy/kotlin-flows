package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Custom operator to filter out even numbers
fun <T> Flow<T>.filterEven(): Flow<T> = flow {
    collect { value ->
        if (value.toString().toInt() % 2 != 0) {
            emit(value)
        }
    }
}

fun main() = runBlocking {
    val numbersFlow = flow {
        for (i in 1..10) {
            emit(i)
        }
    }

    numbersFlow
        .filterEven() // Use the custom operator
        .collect { value ->
            println("Received: $value")
        }
}