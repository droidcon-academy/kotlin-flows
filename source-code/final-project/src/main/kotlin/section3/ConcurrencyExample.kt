package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun numberFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i on ${Thread.currentThread().name}")
        delay(300)
        emit(i)
    }
}.flowOn(Dispatchers.Default) // Switch emission to background thread

fun main() = runBlocking {
    numberFlow()
        .collect { value ->
            println("Collected $value on ${Thread.currentThread().name}")
        }
}