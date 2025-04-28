package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.debug.DebugProbes
import kotlinx.coroutines.flow.*


fun generateNumbersFlow(count: Int): Flow<Int> = flow {
    for (i in 0 until count) {
        delay(300)
        emit(i)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    DebugProbes.install() // Enable coroutine debugging

    val job = launch {
        generateNumbersFlow(10)
            .onEach { value ->
                println("Received: $value")
            }
            .collect()
    }

    delay(1200) // Wait for a few emissions
    println("Cancelling collector...")
    job.cancel() // Cancel the coroutine collecting the Flow
    DebugProbes.dumpCoroutines() // Dump the coroutine states
    println("Collector canceled.")
}