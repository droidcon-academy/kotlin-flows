package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    // Simulated "typing" flow: emits partial words with varying delays
    val typingFlow = flow {
        emit("H")      // user typed "H"
        delay(50)
        emit("He")     // quickly typed "e"
        delay(50)
        emit("Hel")    // quickly typed "l"
        delay(300)     // pause for a moment (> 100ms)
        emit("Hell")   // typed "l" after pause
        delay(50)
        emit("Hello")  // quickly typed "o"
    }

    typingFlow
        .debounce(100)    // wait 100ms of inactivity before emitting
        .collect { value -> println("Collected: $value") }
}