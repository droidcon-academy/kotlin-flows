package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val numbersFlow = flowOf(10, 20, 30)

    numbersFlow
        .onEach { number -> println("Logging: $number") }
        .collect { number -> println("Collected: $number") }
}
