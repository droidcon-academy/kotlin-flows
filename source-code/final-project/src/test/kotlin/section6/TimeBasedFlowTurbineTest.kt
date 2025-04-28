package section6

import kotlinx.coroutines.test.advanceTimeBy
import app.cash.turbine.test
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

// A flow that emits immediately, then waits 1 second before emitting again
fun timeSeparatedFlow(): Flow<String> = flow {
    emit("Hello")
    delay(1000)            // 1 second delay
    emit("World")
}

class TimeBasedFlowTurbineTest {
    @Test
    fun `test flow with time delays`() = runTest {
        val timedFlow = timeSeparatedFlow()
        timedFlow.test {
            // First item should come immediately
            val first = awaitItem()
            assertEquals("Hello", first)

            // After the first item, no second item should be emitted yet (since 1s delay not advanced)
            expectNoEvents()  // Turbine check: nothing emitted in this moment
            // Fast-forward virtual time by 1000ms to trigger the delayed emission
            advanceTimeBy(1000)

            // Now the second item should be available
            val second = awaitItem()
            assertEquals("World", second)

            // Flow should complete after emitting both items
            awaitComplete()
        }
    }
}