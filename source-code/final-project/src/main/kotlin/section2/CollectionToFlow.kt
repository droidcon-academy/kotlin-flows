package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    listOf(1, 2, 3).asFlow()
        .collect { value ->
            println("Received: $value")
        }
}