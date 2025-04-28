package section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main():Unit = runBlocking {
    // Cold flow (same as before)
    val coldFlow: Flow<Int> = flow {
        for (i in 1..5) {
            delay(1000)
            emit(i)
        }
    }
    // Convert cold flow to hot by sharing it (replay = 0 means no replay, start immediately)
    val hotFlow: SharedFlow<Int> = coldFlow.shareIn(
        scope = this,       // scope in which the sharing coroutine lives
        started = SharingStarted.Eagerly,
        replay = 0
    )

    // Collector 1 subscribes immediately
    launch {
        hotFlow.collect { value ->
            println("Collector 1 received $value")
        }
    }

    // Collector 2 subscribes after 2.5 seconds
    delay(2500)
    launch {
        hotFlow.collect { value ->
            println("Collector 2 received $value")
        }
    }
}
