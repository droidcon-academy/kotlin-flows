package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Our Flow builder powered by teamwork!
fun multiSourceFlow(): Flow<Int> = channelFlow {
    launch {
        send(1)         // First coroutine sends 1
        delay(500)      // Simulates delay
        send(2)         // Sends 2
    }
    launch {
        delay(300)      // Starts slightly later
        send(3)         // Sends 3 independently
    }
}

fun main() = runBlocking {
    multiSourceFlow().collect { value ->
        println("Received: $value")
    }
}