package section6

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import kotlin.test.Test
import kotlin.test.assertEquals

class DelayedFlowTest {

    @ExperimentalCoroutinesApi
    @Test
    fun testDelayedFlow() = runTest {
        // Arrange: Create a Flow that emits values with a delay
        val flow = flow {
            emit(1)
            delay(500) // Simulate a 500ms delay
            emit(2)
            delay(500) // Simulate another 500ms delay
            emit(3)
        }

        // Act: Collect the Flow and store the emitted values
        val result = mutableListOf<Int>()
        val job = launch {
            flow.collect { value ->
                result.add(value)
            }
        }

        // Assert: Verify that the Flow emits the correct values at the right time

        // Advance time by 500ms to trigger the first emission
        advanceTimeBy(500)
        assertEquals(listOf(1), result) // Only the first value should be emitted

        // Advance time by another 500ms to trigger the second emission
        advanceTimeBy(500)
        assertEquals(listOf(1, 2), result) // The first and second values should be emitted

        // Advance time by another 500ms to trigger the third emission
        advanceTimeBy(500)
        assertEquals(listOf(1, 2, 3), result) // All three values should be emitted

        // Cancel the job to clean up
        job.cancel()
    }

    @Test
    fun testDelayedFlowWithAdvanceUntilIdle() = runTest {
        // Arrange: Create a Flow that emits values with a delay
        val flow = flow {
            emit(1)
            delay(500)
            emit(2)
            delay(500)
            emit(3)
        }

        // Act: Collect the Flow and store the emitted values
        val result = mutableListOf<Int>()
        flow.collect { value ->
            result.add(value)
        }

        // Assert: Verify that all values were emitted
        assertEquals(listOf(1, 2, 3), result)
    }
}