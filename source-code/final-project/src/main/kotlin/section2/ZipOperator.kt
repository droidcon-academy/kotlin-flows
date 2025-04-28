package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val letters = flowOf("A", "B", "C")
        .onEach { delay(300) }          // emit one letter every 300ms
    val numbers = flowOf(1, 2, 3, 4)
        .onEach { delay(1000) }         // emit one number every 1000ms

    letters.zip(numbers) { letter, number ->
        "$letter$number"
    }.collect { pair ->
        println(pair)
    }
}