package com.shchurovsi.carspecapp.di

import android.app.Application
import com.shchurovsi.carspecapp.data.VehicleRepositoryImpl
import com.shchurovsi.carspecapp.data.local.db.AppDatabase
import com.shchurovsi.carspecapp.data.local.db.VehicleDao
import com.shchurovsi.carspecapp.domain.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Suppress("WARNINGS")
    @ApplicationScope
    @Binds
    fun bindVehicleRepository(impl: VehicleRepositoryImpl): VehicleRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideDao(
            application: Application
        ): VehicleDao {
            return AppDatabase.invoke(application).getVehicleDao()
        }
    }
}
