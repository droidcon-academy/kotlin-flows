package section6

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

// Code under test: a flow with a delay between emissions
fun magicNumbers(): Flow<Int> = flow {
    emit(42)              // first value immediately
    delay(1000)           // simulate a 1-second pause (e.g., heavy computation or network)
    emit(99)              // second value after the delay
}

// Unit test demonstrating virtual time control
class VirtualTimeFlowTest {

    @Test
    fun `magicNumbers flow emits 42 then 99 using virtual time`() = runTest {
        val collectedResults = mutableListOf<Int>()

        // Start collecting the flow in a background coroutine within the test
        val job = launch {
            magicNumbers().collect { value ->
                collectedResults.add(value)
            }
        }

        delay(100.milliseconds)
        // At this point, we have started the flow. Let's examine what has happened so far:
        // The flow should have emitted 42 immediately, but 99 is scheduled to be emitted after 1000ms.
        // Because we haven't advanced time yet, 99 hasn't been emitted.
        // We can assert the intermediate state:
        assertEquals(listOf(42), collectedResults, "After launching, first value should be collected immediately.")

        // Now, fast-forward (advance) the virtual time by 1000 milliseconds to trigger the delayed emission.
        advanceTimeBy(1000)

        // After advancing time, the flow's delay is completed, so 99 should have been emitted.
        assertEquals(listOf(42, 99), collectedResults, "After advancing time, second value should be collected.")

        // Clean up the collecting coroutine
        job.cancel()
    }
}