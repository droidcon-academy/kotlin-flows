package section6

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

// Code under test: a simple flow builder function
fun getNumbers(): Flow<Int> = flow {
    emit(1)
    emit(2)
    emit(3)
}

// Unit test for the getNumbers() flow
class FlowTest {

    @Test
    fun `getNumbers emits 1, 2, 3 in order`() = runTest {
        // Arrange: nothing too complex here, we just prepare the flow
        val numbersFlow = getNumbers()

        // Act: collect the flow's emissions into a list
        val emittedList = numbersFlow.toList()

        // Assert: verify the collected emissions match the expected values
        assertEquals(listOf(1, 2, 3), emittedList)
    }
}