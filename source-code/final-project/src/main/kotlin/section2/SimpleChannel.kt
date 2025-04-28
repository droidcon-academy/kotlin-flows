package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

fun main(): Unit = runBlocking {
    val channel = Channel<Int>()

    launch {
        for (i in 1..5) {
            delay(500)
            channel.send(i) // Send data to the Channel
        }
        channel.close() // Close the Channel when done
    }

    launch {
        for (value in channel) {
            println("Received: $value")
        }
    }
}