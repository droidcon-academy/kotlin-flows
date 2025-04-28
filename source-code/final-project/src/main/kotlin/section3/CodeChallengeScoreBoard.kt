package section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val gameEvents = MutableSharedFlow<String>(replay = 0)
    val score = MutableStateFlow("Home: 0, Away: 0")

    // --- Collector A (attach first before emitting anything) ---
    launch {
        gameEvents.collect { println("Collector A - EVENT: $it") }
    }

    // Slight delay to ensure collector is attached
    delay(50)

    // Now emit Kickoff event
    gameEvents.emit("Kickoff")

    // Start collecting the score
    launch {
        score.collect { println("Collector A - SCORE: $it") }
    }

    // Emit initial score
    delay(50)
    score.value = "Home: 0, Away: 0"

    delay(100)
    gameEvents.emit("Goal - Home")
    score.value = "Home: 1, Away: 0"

    delay(100)
    gameEvents.emit("Goal - Away")
    score.value = "Home: 1, Away: 1"

    // --- Collector B (joins late) ---
    delay(200)
    launch {
        gameEvents.collect { println("Collector B - EVENT: $it") }
    }

    launch {
        score.collect { println("Collector B - SCORE: $it") }
    }

    delay(100)
    gameEvents.emit("Red Card")
    score.value = "Home: 1, Away: 1" // duplicate → StateFlow won’t emit
}