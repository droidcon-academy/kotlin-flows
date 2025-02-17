package dev.sunnat629.section1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("Coffee is ready!")
    }
    println("Brewing coffee...")
    println("Cleaning the counter...")
}
