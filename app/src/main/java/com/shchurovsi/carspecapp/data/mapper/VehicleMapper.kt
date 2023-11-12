package com.shchurovsi.carspecapp.data.mapper

import com.shchurovsi.carspecapp.data.local.model.VehicleDbModel
import com.shchurovsi.carspecapp.domain.entities.Vehicle
import javax.inject.Inject

class VehicleMapper @Inject constructor() {

    fun mapDbModelToEntity(vehicleDbModel: VehicleDbModel) = Vehicle(
        brand = vehicleDbModel.brand,
        motorPower = vehicleDbModel.motorPower.toString(),
        seats = vehicleDbModel.seats,
        image = vehicleDbModel.image,
        id = vehicleDbModel.id
    )

    fun mapEntityToDbModel(vehicle: Vehicle) = VehicleDbModel(
        brand = vehicle.brand,
        motorPower = vehicle.motorPower.toInt(),
        seats = vehicle.seats,
        image = vehicle.image,
        id = vehicle.id
    )
}

