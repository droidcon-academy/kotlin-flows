package dev.sunnat629.section2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun countdownFlow(): Flow<String> = flow {
    for (i in 5 downTo 1) {
        emit(i.toString())
        delay(1000)
    }
    emit("Coundown is done!")
}

fun main() = runBlocking {
    countdownFlow().collect { value ->
        println(value)
    }
}
