package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchSearchResults(query: String): Flow<String> = flow {
    delay(500) // Simulate network call
    emit("Results for: $query")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val searchQueries = flow {
        listOf("Kotlin", "Flow", "Coroutines").forEach {
            delay(300)
            emit(it)
        }
    }

    searchQueries.flatMapLatest { query ->
        fetchSearchResults(query) // Fetch results for the latest query
    }.collect { results ->
        println(results)
    }
}