package com.shchurovsi.carspecapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shchurovsi.carspecapp.VehicleApplication
import com.shchurovsi.carspecapp.databinding.ActivityMainBinding
import com.shchurovsi.carspecapp.presentation.fragments.VehicleListViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val appComponent by lazy {
        (application as VehicleApplication).applicationComponent.activityComponent().create()
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<VehicleListViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
