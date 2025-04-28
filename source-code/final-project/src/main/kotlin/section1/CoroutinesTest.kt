package section1

import kotlinx.coroutines.*

fun main() = runBlocking {
    for (i in 1..10) {
        launch {
            println("Coroutine $i: ${Thread.currentThread().name}")
        }
    }
}