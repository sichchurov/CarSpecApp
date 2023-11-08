package com.shchurovsi.carspecapp.di

import com.shchurovsi.carspecapp.presentation.VehicleActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: VehicleActivity)
}