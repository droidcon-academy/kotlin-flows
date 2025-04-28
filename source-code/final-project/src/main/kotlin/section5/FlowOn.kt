package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun heavyComputationFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(500) // Simulate heavy computation
        emit(i)
    }
}

fun main() = runBlocking {
    heavyComputationFlow()
        .flowOn(Dispatchers.Default) // Run emission on a background thread
        .collect { value ->
            println("Received: $value on ${Thread.currentThread().name}")
        }
}