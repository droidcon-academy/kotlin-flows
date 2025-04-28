package section6

import app.cash.turbine.test
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// A flow that emits two numbers then throws an exception
fun errorProneFlow(): Flow<Int> = flow {
    emit(42)
    emit(100)
    throw RuntimeException("Something went wrong")
}

class ErrorHandlingFlowTurbineTest {
    @Test
    fun `test flow that throws an error`() = runTest {
        val problemFlow = errorProneFlow()
        problemFlow.test {
            // Await first two emissions
            val firstNumber = awaitItem()
            assertEquals(42, firstNumber, "First number should be 42")
            val secondNumber = awaitItem()
            assertEquals(100, secondNumber, "Second number should be 100")

            // Await the error signal from the flow
            val error = awaitError()
            // Check that the error is the expected one
            assertTrue(error is RuntimeException, "Error should be a RuntimeException")
            assertEquals("Something went wrong", error.message)
        }
    }
}
