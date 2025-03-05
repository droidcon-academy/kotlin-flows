package dev.sunnat629.section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main(): Unit = runBlocking {
    val stateFlow = MutableStateFlow("Logged Out")

    // Updater
    launch {
        delay(1000)
        println("Updating state to: Logged In")
        stateFlow.value = "Logged In"
    }

    // Collector
    stateFlow.collect { state ->
        println("Current state: $state")
    }
}