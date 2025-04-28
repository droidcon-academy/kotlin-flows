package section1

import kotlinx.coroutines.*

suspend fun fetchUserData(): String {
    delay(2000L) // Simulate network delay
    return "User data loaded!"
}

fun main() = runBlocking {
    println("Fetching data...")
    val result = fetchUserData()
    println(result)
}