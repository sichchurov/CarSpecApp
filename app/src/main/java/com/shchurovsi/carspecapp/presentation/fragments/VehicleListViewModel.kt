package com.shchurovsi.carspecapp.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shchurovsi.carspecapp.domain.entities.Vehicle
import com.shchurovsi.carspecapp.domain.usecases.AddVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.EditVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class VehicleListViewModel @Inject constructor(
    private val addVehicleItemUseCase: AddVehicleItemUseCase,
    private val editVehicleItemUseCase: EditVehicleItemUseCase,
    private val getVehicleItemUseCase: GetVehicleItemUseCase,
    private val getVehicleListUseCase: GetVehicleListUseCase
) : ViewModel() {

    private val _vehicleList = getVehicleListUseCase()
    val vehicleList: LiveData<List<Vehicle>>
        get() = _vehicleList





    suspend fun addVehicleItem(vehicle: Vehicle) {
        viewModelScope.launch {
            addVehicleItemUseCase(vehicle)
        }
    }
}
