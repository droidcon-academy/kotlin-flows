package section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    // Flow of numbers 1,2,3 with a delay
    val numbers = flow {
        var i = 1
        while (i <= 3) {
            delay(100)        // emit every 100ms
            emit(i)
            i++
        }
    }
    // Flow of letters A,B,C with a different delay
    val letters = flow {
        val chars = listOf("A", "B", "C")
        for (ch in chars) {
            delay(150)       // emit every 150ms
            emit(ch)
        }
    }

    numbers.combine(letters) { num, letter ->
        "$num:$letter"
    }.collect { combinedValue ->
        println(combinedValue)
    }
}