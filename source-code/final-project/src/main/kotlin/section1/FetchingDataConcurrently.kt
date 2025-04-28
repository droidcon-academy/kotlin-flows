package section1

import kotlinx.coroutines.*

fun main() = runBlocking {
    // Launch coroutines to fetch data concurrently
    val startTime = System.currentTimeMillis()

    val userDeferred = async { fetchTheUserData() }.await()
    val ordersDeferred = async { fetchOrders() }.await()
    val profileLikes = async { fetchProfileLikes() }.await()

    val endTime = System.currentTimeMillis()

    println("User Data: $userDeferred")
    println("Orders: $ordersDeferred")
    println("Fetched in ${endTime - startTime} ms")
}

// Mock suspending function to fetch user data
suspend fun fetchTheUserData(): String {
    delay(1000) // Simulating network delay
    return "John Doe"
}

suspend fun fetchProfileLikes() : String {
    delay(2000)
    return "10.2K Likes"
}

// Mock suspending function to fetch orders
suspend fun fetchOrders(): List<String> {
    delay(2000) // Simulating network delay
    return listOf("Coffee", "Bagel", "Sandwich")
}