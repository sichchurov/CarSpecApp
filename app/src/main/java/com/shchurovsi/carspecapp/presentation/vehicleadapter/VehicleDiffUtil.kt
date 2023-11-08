package com.shchurovsi.carspecapp.presentation.vehicleadapter

import androidx.recyclerview.widget.DiffUtil
import com.shchurovsi.carspecapp.domain.entities.Vehicle

object VehicleDiffUtil : DiffUtil.ItemCallback<Vehicle>() {
    override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
        return oldItem == newItem
    }
}
