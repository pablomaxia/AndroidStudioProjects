package com.example.ejemploviewmodel

import java.util.*

class WeatherData{
    private var temperature: Float = 0.0f
    private var humidity: Float = 0.0f
    private var pressure: Float = 0.0f
    private var observers: ArrayList<Observer> = ArrayList<Observer>()

    fun registerObserver(o: Observer){
        observers.add(o)
    }

    fun removeObserver(o: Observer?) {
        val i: Int = observers.indexOf(o)
        if (i >= 0) {
            observers.removeAt(i)
        }
    }

    fun notifyObservers() {
        for (observer in observers) {
            observer.update(temperature, humidity, pressure)
        }
    }

    fun measurementsChanged() {
        notifyObservers()
    }

    fun setMeasurements(temperature: Float, humidity: Float, pressure: Float) {
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure
        measurementsChanged()
    }
}
