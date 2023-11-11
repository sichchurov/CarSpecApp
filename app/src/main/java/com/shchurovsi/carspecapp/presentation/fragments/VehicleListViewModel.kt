package com.shchurovsi.carspecapp.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shchurovsi.carspecapp.domain.entities.Vehicle
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleListUseCase
import javax.inject.Inject

class VehicleListViewModel @Inject constructor(
    private val getVehicleListUseCase: GetVehicleListUseCase
) : ViewModel() {

    val vehicleList: LiveData<List<Vehicle>>
        get() = getVehicleListUseCase()
}
