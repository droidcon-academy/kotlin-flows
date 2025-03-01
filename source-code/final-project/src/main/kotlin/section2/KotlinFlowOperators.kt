package dev.sunnat629.section2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class KotlinFlowOperators {

    private fun simpleFlow(): Flow<Int> = flow {
        for (i in 1..5) {
            emit(i)
            delay(100) // Simulating async work
        }
    }

    fun runMapOperator() = runBlocking {
        simpleFlow()
            .map { value -> "Number: $value" }
            .collect { println(it) }
    }

    fun runFilterOperator() = runBlocking {
        simpleFlow()
            .filter { value -> value % 2 == 0 }
            .collect { println("Even: $it") }
    }

    fun runCombineOperator() = runBlocking {
        val flow1 = flowOf("A", "B", "C")
        val flow2 = flowOf(1, 2, 3)

        flow1.combine(flow2) { a, b -> "$a$b" }
            .collect { println(it) }
    }

    fun runZipOperator() = runBlocking {
        val flow1 = flowOf("A", "B", "C")
        val flow2 = flowOf(1, 2, 3)

        flow1.zip(flow2) { a, b -> "$a$b" }
            .collect { println(it) }
    }

    fun runOnEachOperator() = runBlocking {
        simpleFlow()
            .onEach { println("Processing: $it") }
            .collect()
    }

    fun runTakeOperator() = runBlocking {
        simpleFlow()
            .take(3)
            .collect { println(it) }
    }

    fun runFlatMapConcatOperator() = runBlocking {
        simpleFlow()
            .flatMapConcat { value -> flowOf("A$value", "B$value") }
            .collect { println(it) }
    }

    fun runDebounceOperator() = runBlocking {
        val flow = flow {
            emit(1)
            delay(100)
            emit(2)
            delay(300)
            emit(3)
        }

        flow.debounce(200)
            .collect { println(it) }
    }

    fun runDistinctUntilChangedOperator() = runBlocking {
        val flow = flowOf(1, 1, 2, 2, 3, 3)

        flow.distinctUntilChanged()
            .collect { println(it) }
    }

    fun runCatchOperator() = runBlocking {
        val flow = flow {
            emit(1)
            throw RuntimeException("Error!")
        }

        flow.catch { e -> println("Caught: $e") }
            .collect { println(it) }
    }
}

fun main() {
    val operators = KotlinFlowOperators()

    println("Running Map Operator:")
    operators.runMapOperator()

    println("\nRunning Filter Operator:")
    operators.runFilterOperator()

    println("\nRunning Combine Operator:")
    operators.runCombineOperator()

    println("\nRunning Zip Operator:")
    operators.runZipOperator()

    println("\nRunning OnEach Operator:")
    operators.runOnEachOperator()

    println("\nRunning Take Operator:")
    operators.runTakeOperator()

    println("\nRunning FlatMapConcat Operator:")
    operators.runFlatMapConcatOperator()

    println("\nRunning Debounce Operator:")
    operators.runDebounceOperator()

    println("\nRunning DistinctUntilChanged Operator:")
    operators.runDistinctUntilChangedOperator()

    println("\nRunning Catch Operator:")
    operators.runCatchOperator()
}
