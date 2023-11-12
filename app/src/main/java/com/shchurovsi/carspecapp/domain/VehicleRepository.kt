package com.shchurovsi.carspecapp.domain

import androidx.lifecycle.LiveData
import com.shchurovsi.carspecapp.domain.entities.Vehicle

interface VehicleRepository {
    fun getVehicleList(): LiveData<List<Vehicle>>

    fun getVehicleListByMotorPower(motorPower: Int): LiveData<List<Vehicle>>

    suspend fun addVehicleItem(vehicle: Vehicle)

    suspend fun editVehicleItem(vehicle: Vehicle)

    suspend fun getVehicleItem(vehicleId: Int): Vehicle
}
