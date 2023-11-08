package com.shchurovsi.carspecapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shchurovsi.carspecapp.domain.entities.Vehicle
import com.shchurovsi.carspecapp.domain.usecases.AdVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.EditVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class VehicleViewModel @Inject constructor(
    private val adVehicleItemUseCase: AdVehicleItemUseCase,
    private val editVehicleItemUseCase: EditVehicleItemUseCase,
    private val getVehicleItemUseCase: GetVehicleItemUseCase,
    private val getVehicleListUseCase: GetVehicleListUseCase
) : ViewModel() {

    private val _vehicleList = MutableLiveData<List<Vehicle>>()
    val vehicleList: LiveData<List<Vehicle>>
        get() = _vehicleList

    init {
        getVehicleList()
    }

    private fun getVehicleList() {
        _vehicleList.value = getVehicleListUseCase().value
    }

    suspend fun addVehicleItem(vehicle: Vehicle) {
        viewModelScope.launch {
            adVehicleItemUseCase(vehicle)
        }
    }
}
