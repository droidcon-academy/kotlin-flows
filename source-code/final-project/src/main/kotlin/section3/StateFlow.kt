package section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    // Create a StateFlow with initial value 0
    val counter = MutableStateFlow(0)

    // Collector 1: starts observing from the beginning
    val observer1 = launch (start = CoroutineStart.LAZY) {
        counter.collect { value ->
            println("Observer1 sees count = $value")
        }
    }

    observer1.start()
    delay(50)

    // Update the state a few times
    counter.value = 1
    delay(50)
    counter.value = 2
    delay(50)

    // Collector 2: joins later
    val observer2 = launch(start = CoroutineStart.LAZY) {
        counter.collect { value ->
            println("Observer2 sees count = $value")
        }
    }

    observer2.start()
    delay(50)

    // Further updates to the state
    counter.value = 3
    delay(50)
    counter.value = 3   // setting same value -> will NOT emit (distinct until changed)
    delay(50)

    counter.value = 4
    delay(100)  // small delay to let collectors print
}