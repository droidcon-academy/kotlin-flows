package dev.sunnat629.section1

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    for (i in 1..10) {
        launch {
            println("Coroutine $i: ${Thread.currentThread().name}")
        }
    }
}
