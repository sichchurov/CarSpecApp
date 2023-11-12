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

    private val _isFilterToggled = MutableLiveData(false)
    val isFilterToggled: LiveData<Boolean>
        get() = _isFilterToggled

    val vehicleList: LiveData<List<Vehicle>>
        get() = getVehicleListUseCase()

    val vehicleListByMotorPower: LiveData<List<Vehicle>>
        get() = getVehicleListByMotorPowerUseCase(power.value ?: 0)

    private val _power = MutableLiveData<Int>()
    val power: LiveData<Int>
        get() = _power

    fun toggle(status: Boolean) {
        _isFilterToggled.value = status
    }

    fun filter(minMotorPower: Int) {
        _power.value = minMotorPower
    }
}
