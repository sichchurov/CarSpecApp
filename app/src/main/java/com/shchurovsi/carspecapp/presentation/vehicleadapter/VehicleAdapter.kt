package com.shchurovsi.carspecapp.presentation.vehicleadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shchurovsi.carspecapp.databinding.ItemVehicleBinding
import com.shchurovsi.carspecapp.domain.entities.Vehicle

class VehicleAdapter : ListAdapter<Vehicle, VehicleViewHolder>(VehicleDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        return VehicleViewHolder(
            ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = getItem(position)
        holder.binding.apply {
            tvBrand.text = vehicle.brand
            tvCapacity.text = vehicle.capacity.toString()
            tvMotorPower.text = vehicle.motorPower
        }
    }
}