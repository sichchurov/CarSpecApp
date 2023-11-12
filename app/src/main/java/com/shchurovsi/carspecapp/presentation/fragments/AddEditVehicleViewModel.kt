package com.shchurovsi.carspecapp.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shchurovsi.carspecapp.domain.entities.Vehicle
import com.shchurovsi.carspecapp.domain.usecases.AddVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.EditVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditVehicleViewModel @Inject constructor(
    private val addVehicleItemUseCase: AddVehicleItemUseCase,
    private val editVehicleItemUseCase: EditVehicleItemUseCase,
    private val getVehicleItemUseCase: GetVehicleItemUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val _vehicleItem = MutableLiveData<Vehicle>()
    val vehicleItem: LiveData<Vehicle>
        get() = _vehicleItem

    fun getVehicleItem(vehicleId: Int) {
        viewModelScope.launch {
            val vehicle = getVehicleItemUseCase(vehicleId)
            _vehicleItem.value = vehicle
        }
    }

    fun editVehicle(
        inputBrand: String,
        inputMotorPower: String,
        inputSeats: String,
        image: String
    ) {
        val brand = parseField(inputBrand)
        val motorPower = parseField(inputMotorPower)
        val seats = parseField(inputSeats)

        if (validateFields(inputBrand, inputMotorPower, inputSeats)) {
            _vehicleItem.value?.let { vehicle ->
                viewModelScope.launch {
                    val updatedVehicle = vehicle.copy(
                        brand = brand,
                        motorPower = motorPower,
                        seats = seats,
                        image = image
                    )
                    editVehicleItemUseCase.invoke(updatedVehicle)
                    finishWork()
                }
            }
        }
    }

    fun addVehicle(
        inputBrand: String,
        inputMotorPower: String,
        inputSeats: String,
        image: String
    ) {
        val brand = parseField(inputBrand)
        val motorPower = parseField(inputMotorPower)
        val seats = parseField(inputSeats)

        if (validateFields(inputBrand, inputMotorPower, inputSeats)) {
            viewModelScope.launch {
                addVehicleItemUseCase.invoke(
                    Vehicle(
                        brand,
                        motorPower,
                        seats,
                        image
                    )
                )
                finishWork()
            }
        }
    }

    private fun parseField(inputField: String): String {
        return inputField.trim()
    }

    private fun validateFields(
        inputBrand: String,
        inputMotorPower: String,
        inputSeats: String,
    ): Boolean {
        var result = true

        if (inputBrand.isBlank()) {
            _state.value = State.InputBrandError(true)
            result = false
        }
        if (inputMotorPower.isBlank()) {
            _state.value = State.InputMotorPowerError(true)
            result = false
        }
        if (inputSeats.isBlank()) {
            _state.value = State.InputSeatsError(true)
            result = false
        }
        return result
    }

    private fun finishWork() {
        _state.value = State.ShouldCloseScreenState
    }
}
