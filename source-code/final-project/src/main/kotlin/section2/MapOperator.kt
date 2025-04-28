package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val numbersFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)

    val transformedFlow: Flow<String> = numbersFlow.map { number ->
        "Transformed: $number"
    }

    transformedFlow.collect { value ->
        println(value)
    }
}