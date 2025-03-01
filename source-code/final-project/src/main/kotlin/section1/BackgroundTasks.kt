package dev.sunnat629.section1

import kotlinx.coroutines.*

suspend fun downloadFile(): String {
    delay(3000L) // Simulate file download
    return "File downloaded!"
}

fun main() = runBlocking {
    println("Downloading...")
    val file = downloadFile()
    println(file)
}
