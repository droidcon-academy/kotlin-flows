package section1


import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

import kotlinx.coroutines.flow.flow

fun main() = runBlocking {
    val stream = flow {
        for (i in 1..6) {
            emit(i)
        }
    }

    stream
        .filter { it % 2 == 0 }
        .map { "Number: $it" }
        .collect { println(it) }
}
