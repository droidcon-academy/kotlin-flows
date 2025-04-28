package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Flow builder using the flow { } builder to emit values 1 to 5 with delay
fun buildNumberFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(500) // Simulate work
        emit(i)    // Emit each number
    }
}

fun main() = runBlocking {
    buildNumberFlow().collect { value ->
        println("Received: $value")
    }
}