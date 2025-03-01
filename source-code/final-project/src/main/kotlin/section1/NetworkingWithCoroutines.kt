package dev.sunnat629.section1

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun fetchUserData(): String {
    delay(2000L) // Simulate network delay
    return "User data loaded!"
}
fun main() = runBlocking {
    println("Fetching data...")
    val result = fetchUserData()
    println(result)
}
