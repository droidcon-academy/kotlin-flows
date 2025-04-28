package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val numbersFlow = flowOf(1, 1, 2, 2, 2, 3, 2, 2)

    numbersFlow
        .distinctUntilChanged()
        .collect { println(it) }
}