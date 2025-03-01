package dev.sunnat629.section3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun fetchWeatherData(): Flow<String> = flow {
    val data = "Sunny, 25°C" // Simulate fetching weather data from API
    emit(data)
}

fun main() = runBlocking {
    fetchWeatherData().collect { data ->
        println("Weather data: $data")
    }
}