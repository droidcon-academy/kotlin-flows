package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val flow = flow {
        for (i in 1..10) {
            println("Emitting: $i")
            emit(i)
            delay(100) // Simulate slow emission
        }
    }

    flow.collect { value ->
        println("Collecting: $value")
        delay(300) // Simulate slow collection
    }
}