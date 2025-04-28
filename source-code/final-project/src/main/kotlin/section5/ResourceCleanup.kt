package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchResourceCleanupData(): Flow<Int> = flow {
    try {
        for (i in 1..5) {
            delay(500)
            println("Emitting: $i")
            emit(i)
        }
    } finally {
        println("Cleaning up resources")
    }
}

fun main() = runBlocking {
    val job = launch {
        fetchResourceCleanupData()
            .collect { value ->
                println("Received: $value")
            }
    }

    delay(1500)
    job.cancel() // Cancel the collector
    println("Collector canceled")
}