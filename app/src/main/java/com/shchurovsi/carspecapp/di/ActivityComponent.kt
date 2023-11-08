package com.shchurovsi.carspecapp.di

import com.shchurovsi.carspecapp.presentation.MainActivity
import com.shchurovsi.carspecapp.presentation.fragments.ItemVehicleFragment
import com.shchurovsi.carspecapp.presentation.fragments.ItemVehicleViewModel
import com.shchurovsi.carspecapp.presentation.fragments.VehicleListFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: ItemVehicleFragment)
    fun inject(fragment: VehicleListFragment)
}
