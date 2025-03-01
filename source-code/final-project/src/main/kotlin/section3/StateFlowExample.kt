package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main(): Unit = runBlocking {
    val stateFlow = MutableStateFlow(0)

    // Start updating the state
    launch {
        for (i in 1..5) {
            delay(500)
            stateFlow.value = i
        }
    }

    // Collect the state
    stateFlow.collect { value ->
        println("Current state: $value")
    }
}