package com.example.ejemploviewmodel

import android.util.Log

class CurrentConditionsDisplay(
   var weatherData: WeatherData
) : Observer {
    private var temperature: Float = 0f
    private var humidity: Float = 0f
    init {
        weatherData.registerObserver(this)
    }

    override fun update(temperature: Float, humidity: Float, pressure: Float) {
        this.temperature = temperature
        this.humidity = humidity
        display()
    }

    fun display() {
        Log.d(
            ":::VIEWMODEL",
            "Current conditions: " + temperature
                    + "F degrees and " + humidity + "% humidity"
        )
    }
}