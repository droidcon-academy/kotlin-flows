package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchManifestContent(): Flow<String> = flow {
    while (true) {
        val content = "Updated Content" // Simulate fetching content from API
        emit(content)
        delay(5000) // Fetch every 5 sec
    }
}

fun main(): Unit = runBlocking {
    val sharedFlow = fetchManifestContent().shareIn(this, SharingStarted.Lazily)

    sharedFlow.collect { content ->
        println("Updated content: $content")
    }
}