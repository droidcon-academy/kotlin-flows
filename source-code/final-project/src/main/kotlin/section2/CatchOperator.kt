package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val faultyFlow = flow {
        emit(1)
        emit(2)
        throw RuntimeException("Oops, something went wrong")
    }

    faultyFlow
        .catch { e ->
            println("Caught error: ${e.message}")
            emit(-1)  // emit a fallback value when error occurs
        }
        .collect { value ->
            println("Collected: $value")
        }
}