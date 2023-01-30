package com.example.ejemploviewmodel

interface Observer {
    fun update(
        temperature: Float,
        humidity: Float,
        pressure: Float
    )
}