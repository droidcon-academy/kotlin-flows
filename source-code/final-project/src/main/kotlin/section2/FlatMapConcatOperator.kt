package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val numbers = flowOf(1, 2, 3)

    numbers.flatMapConcat { num ->
        // Each number triggers a new flow of two emissions
        flow {
            emit("$num -> first")
            delay(100)              // simulate some work
            emit("$num -> second")
        }
    }.collect { value ->
        println(value)
    }
}