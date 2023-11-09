package com.shchurovsi.carspecapp.presentation.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shchurovsi.carspecapp.R
import com.shchurovsi.carspecapp.domain.entities.Vehicle
import com.shchurovsi.carspecapp.domain.usecases.AdVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.EditVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditVehicleViewModel @Inject constructor(
    private val adVehicleItemUseCase: AdVehicleItemUseCase,
    private val editVehicleItemUseCase: EditVehicleItemUseCase,
    private val getVehicleItemUseCase: GetVehicleItemUseCase,
    private val getVehicleListUseCase: GetVehicleListUseCase,
    private val application: Application
) : ViewModel() {

    fun addVehicle(
        brand: String,
        motorPower: String,
        capacity: String,
        image: String
    ) {
        viewModelScope.launch {
            adVehicleItemUseCase.invoke(
                Vehicle(
                    application.getString(R.string.brand, brand),
                    application.getString(R.string.motor_power, motorPower),
                    application.getString(R.string.capacity, capacity),
                    image
                )
            )
        }
    }
}
