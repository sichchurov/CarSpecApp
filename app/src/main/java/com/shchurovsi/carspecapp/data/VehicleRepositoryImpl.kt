package com.shchurovsi.carspecapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.shchurovsi.carspecapp.data.local.db.VehicleDao
import com.shchurovsi.carspecapp.data.mapper.VehicleMapper
import com.shchurovsi.carspecapp.domain.VehicleRepository
import com.shchurovsi.carspecapp.domain.entities.Vehicle
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val vehicleDao: VehicleDao,
    private val mapper: VehicleMapper
) : VehicleRepository {
    override fun getVehicleList(): LiveData<List<Vehicle>> {
        return vehicleDao.getVehicleList().map { vehicleDbModels ->
            vehicleDbModels.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun addVehicleItem(vehicle: Vehicle) {
        vehicleDao.addVehicleItem(mapper.mapEntityToDbModel(vehicle))
    }

    override suspend fun editVehicleItem(vehicle: Vehicle) {
        vehicleDao.addVehicleItem(mapper.mapEntityToDbModel(vehicle))
    }

    override suspend fun getVehicleItem(vehicleId: Int): Vehicle {
        return mapper.mapDbModelToEntity(vehicleDao.getVehicleItem(vehicleId))
    }
}
