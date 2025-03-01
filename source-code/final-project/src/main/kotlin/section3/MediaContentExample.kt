package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class MediaContent(val type: String, val quality: String)

val mediaContentFlow = MutableStateFlow<MediaContent?>(null)

fun updateMediaContent(content: MediaContent) {
    mediaContentFlow.value = content
}

fun main() = runBlocking {
    // Collect the media content
    launch {
        mediaContentFlow.collect { content ->
            println("Updated media content: $content")
        }
    }

    // Simulate updating the content
    delay(1000)
    updateMediaContent(MediaContent("Video", "1080p"))
    delay(1000)
    updateMediaContent(MediaContent("Audio", "320kbps"))
}