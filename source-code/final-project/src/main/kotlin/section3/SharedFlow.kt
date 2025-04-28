package section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    // Create a MutableSharedFlow with a replay of 2 (store last 2 values for new subscribers)
    val scoreStream = MutableSharedFlow<Int>(replay = 2)

    // Collector 1 subscribes to the score stream
    launch {
        scoreStream.collect { score ->
            println("Screen1 sees score: $score")
        }
    }

    delay(500)

    // Simulate some scores being emitted
    scoreStream.emit(10)  // first score
    scoreStream.emit(20)  // second score
    scoreStream.emit(30)  // third score

    // Now Collector 2 joins late
    launch {
        scoreStream.collect { score ->
            println("Screen2 sees score: $score")
        }
    }

    delay(500)
    // Emit one more score after Collector 2 has joined
    scoreStream.emit(40)
}