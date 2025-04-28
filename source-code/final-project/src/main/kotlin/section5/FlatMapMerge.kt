package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchData(id: Int): Flow<String> = flow {
    delay(500) // Simulate a network request
    emit("Data for ID $id")
}

fun main() = runBlocking {
    val idsFlow = flow {
        for (i in 1..5) {
            emit(i)
        }
    }

    idsFlow
        .flatMapMerge { id ->
            fetchData(id) // Fetch data for each ID, all running concurrently
        }
        .collect { data ->
            println("Received: $data")
        }
}