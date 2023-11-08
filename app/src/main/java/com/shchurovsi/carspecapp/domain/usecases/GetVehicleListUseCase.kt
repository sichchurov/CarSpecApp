package com.shchurovsi.carspecapp.domain.usecases

import com.shchurovsi.carspecapp.domain.VehicleRepository
import javax.inject.Inject

class GetVehicleListUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    operator fun invoke() = repository.getVehicleList()
}
