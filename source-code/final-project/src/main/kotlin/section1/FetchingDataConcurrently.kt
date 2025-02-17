package dev.sunnat629.section1

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Launch coroutines to fetch data concurrently
    val startTime = System.currentTimeMillis()

    val userDeferred = async { fetchData() }
    val ordersDeferred = async { fetchOrders() }

    // Wait for both to finish
    val userData = userDeferred.await()
    val orders = ordersDeferred.await()

    val endTime = System.currentTimeMillis()

    println("User Data: $userData")
    println("Orders: $orders")
    println("Fetched in ${endTime - startTime} ms")
}

// Mock suspending function to fetch user data
suspend fun fetchData(): String {
    delay(1000) // Simulating network delay
    return "John Doe"
}

// Mock suspending function to fetch orders
suspend fun fetchOrders(): List<String> {
    delay(2000) // Simulating network delay
    return listOf("Coffee", "Bagel", "Sandwich")
}
