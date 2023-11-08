package com.shchurovsi.carspecapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.shchurovsi.carspecapp.R
import com.shchurovsi.carspecapp.VehicleApplication
import com.shchurovsi.carspecapp.databinding.ActivityVehicleBinding
import javax.inject.Inject

class VehicleActivity : AppCompatActivity() {

    private val appComponent by lazy {
        (application as VehicleApplication).applicationComponent.activityComponent().create()
    }

    private val binding by lazy {
        ActivityVehicleBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<VehicleViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}