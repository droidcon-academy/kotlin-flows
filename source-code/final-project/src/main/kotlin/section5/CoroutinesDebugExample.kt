@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.sunnat629.section5
import kotlinx.coroutines.*
import kotlinx.coroutines.debug.DebugProbes
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    // Enable coroutine debugging
    DebugProbes.install()

    val job = launch {
        mockDebugFetchedData().collect { value ->
            println("Received: $value")
        }
    }

    // Allow some time for coroutines to run
    delay(600)

    // Dump the current coroutine states
    DebugProbes.dumpCoroutines()

    // Cancel the coroutine
    job.cancel()
    println("Collector canceled")
}

// Simulated Flow fetching data
fun mockDebugFetchedData(): Flow<Int> = flow {
    repeat(5) {
        emit(it)
        delay(400)
    }
}