package section4

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // A simple flow that emits 1 then throws an exception
    val faultyFlow = flow<Int> {
        emit(1)
        throw RuntimeException("Boom!")  // This will terminate the flow with an error
    }

    faultyFlow
        .onCompletion { cause ->
            if (cause != null) {
                println("Flow completed exceptionally with $cause")
            } else {
                println("Flow completed normally")
            }
        }
        .catch { e ->
            // Handle the exception by emitting a fallback value
            println("Caught an exception: ${e.message}")
            emit(-1)  // Fallback emission when an error occurs
        }
        .collect { value ->
            println("Collected $value")
            // (If an exception were thrown here in collect {}, catch above would NOT catch it)
        }
}