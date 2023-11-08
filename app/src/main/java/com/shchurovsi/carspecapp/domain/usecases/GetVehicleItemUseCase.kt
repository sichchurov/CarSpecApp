package com.shchurovsi.carspecapp.domain.usecases

import com.shchurovsi.carspecapp.domain.VehicleRepository
import com.shchurovsi.carspecapp.domain.entities.Vehicle

class GetVehicleItemUseCase(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(vehicleId: Int) = repository.getVehicleItem(vehicleId)
}
