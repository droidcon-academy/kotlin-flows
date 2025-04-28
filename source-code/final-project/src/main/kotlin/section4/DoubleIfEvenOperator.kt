package section4

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/** Custom operator that doubles the value if it's even, and skips it if odd */
fun Flow<Int>.doubleIfEven(): Flow<Int> = transform { number ->
    if (number % 2 == 0) {
        emit(number * 2)    // Only emit if the number is even (emit double of it)
    }
    // If the number is odd, emit nothing (effectively filtering it out)
}

// Simple usage example
fun main() = runBlocking {
    val numbers = flowOf(1, 2, 3, 4)
    numbers
        .doubleIfEven()
        .collect { value -> println(value) }
}