package com.shchurovsi.carspecapp.di

import androidx.lifecycle.ViewModel
import com.shchurovsi.carspecapp.presentation.VehicleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("WARNINGS")
@Module
interface ViewModelModule {

    @ViewModelKey(VehicleViewModel::class)
    @IntoMap
    @Binds
    fun bindVehicleViewModel(impl: VehicleViewModel): ViewModel
}
