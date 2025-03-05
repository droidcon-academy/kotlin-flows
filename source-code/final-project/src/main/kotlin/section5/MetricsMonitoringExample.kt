package dev.sunnat629.section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class FlowMetrics(
    var emittedValues: Int,
    var processingTime: Long,
    var errorCount: Int
)

fun fetchFlowMetricsData(): Flow<String> = flow {
    for (i in 1..3) {
        delay(500) // Simulate network request
        emit("Data $i from API")
    }
}

fun main() = runBlocking {
    val metrics = FlowMetrics(0, 0, 0)
    val startTime = System.currentTimeMillis()

    fetchFlowMetricsData()
        .onEach { value ->
            metrics.emittedValues++
            println("Received: $value")
        }
        .catch { e ->
            metrics.errorCount++
            println("Error: ${e.message}")
        }
        .onCompletion {
            metrics.processingTime = System.currentTimeMillis() - startTime
            println("Flow Metrics: $metrics")
        }
        .collect()
}