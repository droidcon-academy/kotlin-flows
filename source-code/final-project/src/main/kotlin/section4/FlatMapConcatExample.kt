package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class UserDetail(val id: Int, val name: String)

fun fetchUserDetail(id: Int): Flow<UserDetail> = flow {
    delay(500) // Simulate network call
    emit(UserDetail(id, "User$id"))
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val userIds = flow {
        listOf(1, 2, 3).forEach {
            delay(300)
            emit(it)
        }
    }

    userIds.flatMapConcat { id ->
        fetchUserDetail(id) // Transform each ID into a Flow of UserDetail
    }.collect { userDetail ->
        println("User Detail: ${userDetail.id}, ${userDetail.name}")
    }
}