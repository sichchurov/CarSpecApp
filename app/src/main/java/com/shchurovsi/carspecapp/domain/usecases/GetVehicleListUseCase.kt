package com.shchurovsi.carspecapp.domain.usecases

import com.shchurovsi.carspecapp.domain.VehicleRepository

class GetVehicleListUseCase(
    private val repository: VehicleRepository
) {
    operator fun invoke() = repository.getVehicleList()
}
