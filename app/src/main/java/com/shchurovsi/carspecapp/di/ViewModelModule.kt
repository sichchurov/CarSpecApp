package com.shchurovsi.carspecapp.di

import androidx.lifecycle.ViewModel
import com.shchurovsi.carspecapp.presentation.fragments.ItemVehicleViewModel
import com.shchurovsi.carspecapp.presentation.fragments.VehicleListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("WARNINGS")
@Module
interface ViewModelModule {

    @ViewModelKey(VehicleListViewModel::class)
    @IntoMap
    @Binds
    fun bindVehicleViewModel(impl: VehicleListViewModel): ViewModel

    @ViewModelKey(ItemVehicleViewModel::class)
    @IntoMap
    @Binds
    fun bindItemVehicleViewModel(impl: ItemVehicleViewModel): ViewModel
}
