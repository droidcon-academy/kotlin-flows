package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun simpleFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(500) // Simulate time-consuming work
        emit(i)    // Send the value downstream
    }
}

fun main() = runBlocking {
    simpleFlow().collect { value ->
        println("Received: $value")
    }
}