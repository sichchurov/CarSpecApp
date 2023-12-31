package com.shchurovsi.carspecapp.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shchurovsi.carspecapp.data.local.model.VehicleDbModel

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicle_info_list ORDER BY brand")
    fun getVehicleList(): LiveData<List<VehicleDbModel>>

    @Query("SELECT * FROM vehicle_info_list WHERE motorPower >= :minMotorPower  ORDER BY brand")
    fun getVehicleListByMotorPower(minMotorPower: Int): LiveData<List<VehicleDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVehicleItem(vehicleDbModel: VehicleDbModel)

    @Query("SELECT * FROM vehicle_info_list WHERE id = :vehicleId LIMIT 1")
    suspend fun getVehicleItem(vehicleId: Int): VehicleDbModel

}
