package section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    // A fast producer: emits numbers 1 to 5 as quickly as possible
    val fastFlow = flow {
        for (i in 1..5) {
            println("Emitting $i")
            emit(i)           // emit without any delay
        }
    }

    // A slow consumer: collects with a 200ms processing delay for each item
    fastFlow.collect { value ->
        delay(200)            // simulate slow work for each value
        println("Collected $value")
    }
}