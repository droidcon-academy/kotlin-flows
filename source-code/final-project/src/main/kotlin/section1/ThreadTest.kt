package section1

fun main() {
    for (i in 1..10) {
        Thread {
            println("Thread $i: ${Thread.currentThread().name}... ")
        }.start()
    }
    Thread.sleep(1000) // To ensure all threads finish before program exits
}