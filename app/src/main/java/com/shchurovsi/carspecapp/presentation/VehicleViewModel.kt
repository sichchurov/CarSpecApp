package com.shchurovsi.carspecapp.presentation

import androidx.lifecycle.ViewModel
import com.shchurovsi.carspecapp.domain.usecases.AdVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.EditVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleItemUseCase
import com.shchurovsi.carspecapp.domain.usecases.GetVehicleListUseCase
import javax.inject.Inject

class VehicleViewModel @Inject constructor(
    private val adVehicleItemUseCase: AdVehicleItemUseCase,
    private val editVehicleItemUseCase: EditVehicleItemUseCase,
    private val getVehicleItemUseCase: GetVehicleItemUseCase,
    private val getVehicleListUseCase: GetVehicleListUseCase
) : ViewModel() {


}
