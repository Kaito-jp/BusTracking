package com.example.bustracking.models

data class BusRoute(
    val id: String = "",
    val name: String = "",
    val terminal: String = "",
    val timeRemaining: String = "",
    val status: String = "ON TIME" // ON TIME, DELAYED
)
