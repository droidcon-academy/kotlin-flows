package section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


fun main(): Unit = runBlocking {
    // A cold flow that emits 1 to 5, one number per second
    val coldFlow: Flow<Int> = flow {
        for (i in 1..5) {
            delay(1000)         // simulate work
            emit(i)             // emit next number
        }
    }


    // Collector 1 starts immediately
    launch {
        coldFlow.collect { value ->
            println("Collector 1 received $value")
        }
    }


    // Wait 2.5 seconds, then start Collector 2
    delay(2500)
    launch {
        coldFlow.collect { value ->
            println("Collector 2 received $value")
        }
    }
}