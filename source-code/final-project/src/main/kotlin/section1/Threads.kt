package section1

fun main() {
    println("Main thread starts")

    // Create and start a new thread
    val worker = Thread {
        for (i in 1..5) {
            println("Worker thread: $i")
            Thread.sleep(500) // wait for half a second
        }
    }

    worker.start() // start the new thread

    println("Main thread continues")

    // Wait for the worker thread to finish
    worker.join()

    println("Main thread ends")
}