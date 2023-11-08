package com.shchurovsi.carspecapp

import android.app.Application
import com.shchurovsi.carspecapp.di.DaggerApplicationComponent

class VehicleApplication : Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
