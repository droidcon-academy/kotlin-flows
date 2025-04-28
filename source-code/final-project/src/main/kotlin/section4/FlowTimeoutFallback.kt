package section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
fun main() = runBlocking {
    // A flow that emits 1, 2, 3 with delays, and then tries to emit 4 after a long delay
    val flowWithDelays = flow {
        emit(1)
        delay(100)    // short delay
        emit(2)
        delay(100)    // short delay
        emit(3)
        delay(1000)   // long delay that will exceed our timeout
        emit(4)       // this won't get emitted due to timeout
    }

    flowWithDelays
        .timeout(200.milliseconds)   // if no emission occurs within 200ms, timeout
        .catch { e ->
            if (e is TimeoutCancellationException) {
                println("Timeout occurred, emitting fallback")
                emit(-1)  // emit fallback value when timeout happens
            } else {
                throw e  // rethrow any other exceptions (let others propagate)
            }
        }
        .collect { value ->
            println("Collected $value")
        }
}