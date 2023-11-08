package com.shchurovsi.carspecapp.domain.usecases

import com.shchurovsi.carspecapp.domain.VehicleRepository
import com.shchurovsi.carspecapp.domain.entities.Vehicle

class EditVehicleItemUseCase(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(vehicle: Vehicle) {
        repository.editVehicleItem(vehicle)
    }
}
