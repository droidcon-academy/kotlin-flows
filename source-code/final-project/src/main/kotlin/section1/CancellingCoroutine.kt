package section1

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(100) {
                println("Working on task $it...")
                delay(200L)
            }
        } finally {
            println("Cleaning up after cancellation...")
        }
    }

    delay(1000L) // Let the coroutine work for a while
    println("Canceling the job...")
    job.cancelAndJoin() // Cancel the coroutine and wait for its completion
    println("Job canceled successfully.")
}