package dev.sunnat629.section1

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val stream = flow {
        emit("Item1")
        emit("Item2 (Damaged)")
        emit("Item3")
    }

    stream
        .map { it.uppercase() }
        .filter { it.contains("Damaged", ignoreCase = true) }
        .collect { println("Removed: $it") }
}