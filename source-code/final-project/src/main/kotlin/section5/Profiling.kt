package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun profilingFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(500) // Heavy work simulation
        emit(i)
    }
}

fun main(): Unit = runBlocking {
    profilingFlow()
        .flowOn(Dispatchers.Default) // Offload heavy work
        .collect { value ->
            println("Received: $value")
        }
}