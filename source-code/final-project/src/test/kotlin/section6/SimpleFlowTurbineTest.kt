package section6

import app.cash.turbine.test
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

// A simple flow that emits three values and then completes
fun simpleStringFlow(): Flow<String> = flow {
    emit("Kotlin")
    emit("Flow")
    emit("Turbine")
    // Flow completes naturally after emitting all values
}

class SimpleFlowTurbineTest {
    @Test
    fun `test simple flow emits expected values`() = runTest {
        val testFlow = simpleStringFlow()  // Get the flow to test
        testFlow.test {
            // 1. Await the first emission from the flow
            val firstItem = awaitItem()
            assertEquals("Kotlin", firstItem, "First item should be 'Kotlin'")

            // 2. Await the second emission
            val secondItem = awaitItem()
            assertEquals("Flow", secondItem, "Second item should be 'Flow'")

            // 3. Await the third emission
            val thirdItem = awaitItem()
            assertEquals("Turbine", thirdItem, "Third item should be 'Turbine'")

            // 4. Await flow completion
            awaitComplete()
        }
    }
}