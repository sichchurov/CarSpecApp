package com.shchurovsi.carspecapp.domain.usecases

import com.shchurovsi.carspecapp.domain.VehicleRepository
import com.shchurovsi.carspecapp.domain.entities.Vehicle

class AdVehicleItemUseCase(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(vehicle: Vehicle) {
        repository.addVehicleItem(vehicle)
    }
}

