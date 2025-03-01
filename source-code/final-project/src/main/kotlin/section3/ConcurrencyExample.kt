package dev.sunnat629.section3

import kotlinx.coroutines.*

fun main() = runBlocking {
    // Launching coroutine in a non-main thread using Dispatchers.IO
    launch(Dispatchers.IO) {
        runBackgroundTask()
    }
    delay(2000)
}

suspend fun runBackgroundTask() {
    println("Running on thread: ${Thread.currentThread().name}")
    delay(1000) // Simulating a background task
    println("Task completed on thread: ${Thread.currentThread().name}")
}