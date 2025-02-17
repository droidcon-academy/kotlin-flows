package dev.sunnat629.section1

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val numbers = (1..10).asFlow()
    numbers
        .filter { it % 2 == 0 }
        .map { it * it }
        .collect { value ->
            println("Processed number: $value")
        }
}

