package section6

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleFlowTest {

    @Test
    fun testSimpleFlow() = runTest {
        // Arrange: Create a simple Flow that emits numbers 1 to 3
        val flow = flow {
            emit(1)
            emit(2)
            emit(3)
        }

        // Act: Collect the Flow and store the emitted values
        val result = mutableListOf<Int>()
        flow.collect { value ->
            result.add(value)
        }

        // Assert: Verify that the Flow emitted the correct values
        assertEquals(listOf(1, 2, 3), result)
    }
}