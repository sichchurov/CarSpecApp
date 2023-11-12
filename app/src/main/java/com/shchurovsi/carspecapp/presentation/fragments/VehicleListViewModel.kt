package com.shchurovsi.carspecapp.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shchurovsi.carspecapp.domain.entities.Vehicle
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleListByMotorPowerUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleListUseCase
import javax.inject.Inject

class VehicleListViewModel @Inject constructor(
    private val getVehicleListUseCase: GetVehicleListUseCase,
    private val getVehicleListByMotorPowerUseCase: GetVehicleListByMotorPowerUseCase
) : ViewModel() {

    private var power = 0

    val vehicleList: LiveData<List<Vehicle>>
        get() = getVehicleListUseCase()

    private val _vehicleListByMotorPower = MutableLiveData<List<Vehicle>>()
    val vehicleListByMotorPower: LiveData<List<Vehicle>>
        get () = getVehicleListByMotorPowerUseCase(power)
    fun filter(minMotorPower: Int) {
       power = minMotorPower
    }
}
