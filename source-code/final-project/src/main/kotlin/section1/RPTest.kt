package section1

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val numbers = (1..5).asFlow()
    numbers.collect { value ->
        println("Got number: $value. ")
    }
}