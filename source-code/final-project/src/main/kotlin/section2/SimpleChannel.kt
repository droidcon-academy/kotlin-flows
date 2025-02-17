package dev.sunnat629.section2

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
