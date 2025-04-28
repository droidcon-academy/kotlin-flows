package section1

import kotlinx.coroutines.*

fun main() = runBlocking {
    val result = async {
        delay(500L) // Simulate computation
        10 * 2
    }
    println("Result: ${result.await()}")
}