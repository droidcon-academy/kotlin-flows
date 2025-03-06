package section6

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BestPracticesTest {

    @Test
    fun testFlowEmissions() = runTest {
        val flow = flow {
            emit(1)
            emit(2)
            emit(3)
        }

        flow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun testFlowErrorHandling() = runTest {
        val flow = flow {
            emit(1)
            throw RuntimeException("Error!")
        }

        flow.test {
            assertEquals(1, awaitItem())
            val error = awaitError()
            assertTrue(error is RuntimeException)
            assertEquals("Error!", error.message)
        }
    }

    @Test
    fun testEmptyFlow() = runTest {
        val flow = flow<Int> { } // Empty Flow

        flow.test {
            awaitComplete() // Verify that the Flow completes without emitting any values
        }
    }

    @Test
    fun `Flow emits correct values in order`() = runTest {
        val flow = flow {
            emit(1)
            emit(2)
            emit(3)
        }

        flow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `Flow throws exception and handles error correctly`() = runTest {
        val flow = flow {
            emit(1)
            throw RuntimeException("Error!")
        }

        flow.test {
            assertEquals(1, awaitItem())
            val error = awaitError()
            assertTrue(error is RuntimeException)
            assertEquals("Error!", error.message)
        }
    }

    @Test
    fun testTimeBasedFlow() = runTest {
        val flow = flow {
            emit(1)
            delay(500)
            emit(2)
            delay(500)
            emit(3)
        }

        flow.test {
            assertEquals(1, awaitItem())
            advanceTimeBy(500) // Fast-forward time by 500ms
            assertEquals(2, awaitItem())
            advanceTimeBy(500) // Fast-forward time by another 500ms
            assertEquals(3, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun testComplexFlow() = runTest {
        val flow = flow {
            emit(1)
            delay(500)
            emit(2)
            delay(500)
            throw RuntimeException("Error!")
        }

        flow.test {
            assertEquals(1, awaitItem())
            advanceTimeBy(500)
            assertEquals(2, awaitItem())
            advanceTimeBy(500)
            val error = awaitError()
            assertTrue(error is RuntimeException)
            assertEquals("Error!", error.message)
        }
    }

}