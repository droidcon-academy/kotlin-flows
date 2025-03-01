package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


fun main(): Unit = runBlocking {
    val sharedFlow = MutableSharedFlow<String>()

    // Start emitting messages
    launch {
        sharedFlow.emit("Hello!")
        delay(1000)
        sharedFlow.emit("How are you?")
    }

    // Collect messages
    sharedFlow.collect { message ->
        println("Received: $message")
    }
}