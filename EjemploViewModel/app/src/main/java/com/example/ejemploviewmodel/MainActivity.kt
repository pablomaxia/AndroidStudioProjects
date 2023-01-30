package com.example.ejemploviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var weatherData: WeatherData = WeatherData()
        var currentDisplay: CurrentConditionsDisplay =
            CurrentConditionsDisplay(weatherData = weatherData)

        weatherData.setMeasurements(80f, 65f, 30.4f);
        weatherData.setMeasurements(82f, 70f, 29.2f);
        weatherData.setMeasurements(78f, 90f, 29.2f);

        currentDisplay.display()
    }
}