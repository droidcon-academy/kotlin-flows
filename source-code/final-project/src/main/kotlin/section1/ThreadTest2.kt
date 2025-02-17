package dev.sunnat629.section1

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("Program starts: ${Thread.currentThread().name}")

    // runBlocking creates a coroutine that blocks the main thread
    runBlocking {
        println("runBlocking starts: ${Thread.currentThread().name}")

        // launch creates a coroutine that runs in a non-blocking manner
        launch {
            println("launch starts: ${Thread.currentThread().name}")
            delay(1000L) // Simulate some work
            println("launch ends: ${Thread.currentThread().name}")
        }
        // async creates a coroutine and returns a Deferred, which can be awaited for a result
        val deferredResult = async {
            println("async starts: ${Thread.currentThread().name}")
            delay(500L) // Simulate some work
            "Result from async"
        }
        println("Waiting for async result...")
        println("Received: ${deferredResult.await()}") // Await the result of async

        println("runBlocking ends: ${Thread.currentThread().name}")
    }
    println("Program ends: ${Thread.currentThread().name}")
}