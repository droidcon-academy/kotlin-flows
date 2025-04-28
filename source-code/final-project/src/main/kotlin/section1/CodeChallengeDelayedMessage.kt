package section1

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Starting coroutine...")
    delay(1000L) // Wait for 1 second
    println("Coroutine finished!")
}