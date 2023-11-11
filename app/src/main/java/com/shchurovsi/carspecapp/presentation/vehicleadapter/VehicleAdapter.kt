package com.shchurovsi.carspecapp.presentation.vehicleadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.shchurovsi.carspecapp.databinding.ItemVehicleBinding
import com.shchurovsi.carspecapp.domain.entities.Vehicle

class VehicleAdapter : ListAdapter<Vehicle, VehicleViewHolder>(VehicleDiffUtil) {

    private var onItemClickListener: ((Vehicle) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        return VehicleViewHolder(
            ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = getItem(position)
        holder.binding.apply {
            tvBrand.text = vehicle.brand
            tvSeats.text = vehicle.seats
            tvMotorPower.text = vehicle.motorPower
            Glide.with(root).load(vehicle.image).into(ivVehicleImage)
            ivVehicleImage.setOnClickListener {
                onItemClickListener?.invoke(vehicle)
            }
        }
    }

    fun setOnItemClickListener(listener: (Vehicle) -> Unit) {
        onItemClickListener = listener
    }
}
