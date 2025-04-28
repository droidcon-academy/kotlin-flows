package section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun tickerFlow(): Flow<Int> = flow {
    var i = 1
    while (true) {                    // infinite stream of numbers
        delay(100)                    // wait 100ms between emissions
        println("Emitting $i")
        emit(i++)
    }
}

fun main() = runBlocking {
    // Collect the flow for 250ms and then cancel
    withTimeoutOrNull(250) {
        tickerFlow().collect { value ->
            println("Collected $value")
        }
    }
    println("Cancelled collecting the flow.")
}