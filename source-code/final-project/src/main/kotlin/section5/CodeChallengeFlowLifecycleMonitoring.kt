package section5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simulateFileDownloads(): Flow<String> = flow {
    for (i in 1..5) {
        delay(400) // Simulate time taken to "download" each file
        if (i == 4) throw RuntimeException("Network error during file download $i") // Simulate a failure
        emit("File $i downloaded")
    }
}

fun main() = runBlocking {
    simulateFileDownloads()
        .onStart {
            println("Starting file downloads...")
        }
        .onEach { file ->
            println("Downloading: $file")
        }
        .onCompletion { cause ->
            if (cause == null) {
                println("All files downloaded successfully!")
            } else {
                println("Download process ended with an error.")
            }
        }.catch { e ->
            println("Error caught: ${e.message}")
        }
        .collect()
}