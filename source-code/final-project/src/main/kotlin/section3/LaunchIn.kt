package section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// A flow of 3 events (numbers 1,2,3) with a short delay between each
fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking {
    // Launch collection of the flow in this runBlocking scope
    events().onEach { event ->
        println("Event: $event")
    }.launchIn(this)  // launchIn starts the flow collection asynchronously

    println("Done")
    // runBlocking will keep running until its child coroutine (launched by launchIn) completes
}