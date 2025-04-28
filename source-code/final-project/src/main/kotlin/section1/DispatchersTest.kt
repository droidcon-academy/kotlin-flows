package section1

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    // Using Dispatchers.Default
    launch(Dispatchers.Default) {
        println("Running on Default Dispatcher: ${Thread.currentThread().name}")
    }

    // Using Dispatchers.IO
    launch(Dispatchers.IO) {
        println("Running on IO Dispatcher: ${Thread.currentThread().name}")
    }

    // Using Dispatchers.Unconfined
    launch(Dispatchers.Unconfined) {
        println("Running on Unconfined Dispatcher: ${Thread.currentThread().name}")
    }

    println("Main program ends: ${Thread.currentThread().name}")
}