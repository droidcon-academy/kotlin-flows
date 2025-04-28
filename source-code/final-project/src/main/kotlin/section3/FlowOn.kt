package section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Utility to log the current thread with a message
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

// A simple flow that emits three numbers with heavy computation (simulated by Thread.sleep)
fun numberFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(100)            // Simulate heavy computation
        log("Emitting $i")           // Log the emission
        emit(i)                      // Emit the value
    }
}
.flowOn(Dispatchers.Default) // Apply flowOn to run the above emissions on a background thread

fun main() = runBlocking {
    numberFlow().collect { value ->
        log("Collected $value")      // Collect on the main thread (runBlocking's context)
    }
}