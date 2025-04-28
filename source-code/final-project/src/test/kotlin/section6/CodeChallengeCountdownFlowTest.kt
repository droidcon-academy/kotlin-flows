package section6

import app.cash.turbine.test
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

// Countdown Flow function: emits from start down to 0 with 1-second delay between each emission
fun countdownFlow(start: Int): Flow<Int> = flow {
    for (i in start downTo 0) {
        emit(i)
        delay(1000)
    }
}

class CountdownFlowTest {

    @Test
    fun `countdownFlow emits numbers from start to 0 every second`() = runTest {
        val flow = countdownFlow(3)

        flow.test {
            // 3 should be emitted immediately
            assertEquals(3, awaitItem())

            // Advance time by 1 second to get the next emission
            advanceTimeBy(1000)
            assertEquals(2, awaitItem())

            advanceTimeBy(1000)
            assertEquals(1, awaitItem())

            advanceTimeBy(1000)
            assertEquals(0, awaitItem())

            // After reaching 0, the flow should complete
            awaitComplete()
        }
    }
}