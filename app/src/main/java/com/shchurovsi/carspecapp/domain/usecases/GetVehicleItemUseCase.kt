package com.shchurovsi.carspecapp.domain.usecases

import com.shchurovsi.carspecapp.domain.VehicleRepository
import javax.inject.Inject

class GetVehicleItemUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(vehicleId: Int) = repository.getVehicleItem(vehicleId)
}
