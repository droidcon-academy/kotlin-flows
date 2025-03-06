package section6

import app.cash.turbine.test
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleFlowTurbineTest {

    @Test
    fun testSimpleFlow() = runTest {
        // Arrange: Create a simple Flow that emits numbers 1 to 3
        val flow = flow {
            emit(1)
            emit(2)
            emit(3)
        }

        // Act and Assert: Use Turbine to verify the emitted values
        flow.test {
            assertEquals(1, awaitItem()) // Verify the first emission
            assertEquals(2, awaitItem()) // Verify the second emission
            assertEquals(3, awaitItem()) // Verify the third emission
            awaitComplete() // Verify that the Flow completes
        }
    }
}