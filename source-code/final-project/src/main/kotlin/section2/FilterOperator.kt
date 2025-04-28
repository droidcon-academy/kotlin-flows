package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val numbersFlow = flowOf(1, 2, 3, 4, 5, 6)

    val evenNumbersFlow = numbersFlow.filter { number ->
        number % 2 == 0    // only even numbers pass
    }

    evenNumbersFlow.collect { println(it) }
}