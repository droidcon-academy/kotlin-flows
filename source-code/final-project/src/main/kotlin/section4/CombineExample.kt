package dev.sunnat629.section4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class WeatherUpdate(val temperature: Int, val humidity: Int)

fun main() = runBlocking {
    val temperatureFlow = flow {
        listOf(25, 26, 27).forEach {
            delay(500)
            emit(it)
        }
    }

    val humidityFlow = flow {
        listOf(60, 65, 70).forEach {
            delay(700)
            emit(it)
        }
    }

    temperatureFlow.combine(humidityFlow) { temp, humidity ->
        WeatherUpdate(temp, humidity) // Merge into a WeatherUpdate object
    }.collect { update ->
        println("Weather Update: ${update.temperature}°C, ${update.humidity}%")
    }
}