package com.shchurovsi.carspecapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment

class ItemVehicleFragment : Fragment() {


    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val VEHICLE_ITEM_ID = "extra_todo_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val UNDEFINED_EXTRA_VALUE = ""

        fun newInstanceItemVehicleFragment(): ItemVehicleFragment {
            return ItemVehicleFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItemVehicleFragment(vehicleItemId: Int): ItemVehicleFragment {
            return ItemVehicleFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(VEHICLE_ITEM_ID, vehicleItemId)
                }
            }
        }
    }
}
