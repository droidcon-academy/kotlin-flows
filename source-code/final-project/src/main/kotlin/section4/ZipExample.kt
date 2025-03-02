package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class User(val id: Int, val name: String)

fun main() = runBlocking {
    val userIds = flow {
        listOf(1, 2, 3).forEach {
            delay(500)
            emit(it)
        }
    }

    val userNames = flow {
        listOf("Alice", "Bob", "Charlie").forEach {
            delay(700)
            emit(it)
        }
    }

    userIds.zip(userNames) { id, name ->
        User(id, name) // Combine into a User object
    }.collect { user ->
        println("User: ${user.id}, ${user.name}")
    }
}