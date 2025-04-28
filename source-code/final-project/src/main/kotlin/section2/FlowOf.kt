package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    flowOf("A", "B", "C")
        .collect { value ->
            println("Received: $value")
        }
}