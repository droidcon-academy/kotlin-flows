package section1

import kotlinx.coroutines.*

fun main() = runBlocking {
    GlobalScope.launch {
        repeat(10) {
            println("Global coroutine $it running")
            delay(500L)
        }
    }

    println("Exiting main...")
    delay(1000L) // Main thread ends, but GlobalScope coroutines continue
}

