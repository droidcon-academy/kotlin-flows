package section6

import app.cash.turbine.test
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ErrorHandlingFlowTurbineTest {

    @Test
    fun testErrorHandlingFlow() = runTest {
        // Arrange: Create a Flow that throws an exception
        val flow = flow {
            emit(1)
            emit(2)
            throw RuntimeException("Error!")
        }

        // Act and Assert: Use Turbine to verify the emitted values and error
        flow.test {
            assertEquals(1, awaitItem()) // Verify the first emission
            assertEquals(2, awaitItem()) // Verify the second emission
            val error = awaitError() // Wait for the error
            assertTrue(error is RuntimeException) // Verify the error type
            assertEquals("Error!", error.message) // Verify the error message
        }
    }
}