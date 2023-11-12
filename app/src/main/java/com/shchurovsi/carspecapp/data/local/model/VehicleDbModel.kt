package com.shchurovsi.carspecapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_info_list")
data class VehicleDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val brand: String,
    val motorPower: Int,
    val seats: String,
    val image: String
)
