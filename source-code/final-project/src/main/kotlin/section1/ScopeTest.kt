package dev.sunnat629.section1

import kotlinx.coroutines.*

fun main() = runBlocking {
    val customScope = CoroutineScope(Dispatchers.Default)

    customScope.launch {
        repeat(5) {
            println("Task $it running on ${Thread.currentThread().name}")
            delay(500L)
        }
    }

    delay(1200L) // Let some tasks complete
    println("Canceling the scope...")
    customScope.cancel() // Cancel the scope and its coroutines
    delay(1000L) // Ensure no more tasks are running
    println("Scope is cleaned up!")
}