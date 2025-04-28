package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val infiniteNumbers = generateSequence(1) { it + 1 }.asFlow()  // 1, 2, 3, ...

    infiniteNumbers
        .collect { println(it) }

    println("Done")
}