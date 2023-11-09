package com.shchurovsi.carspecapp.domain.entities

data class Vehicle(
    val brand: String,
    val motorPower: String,
    val capacity: String,
    val image: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
