package section1

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("Coffee is ready!")
    }
    println("Brewing coffee...")
    println("Cleaning the counter...")
}