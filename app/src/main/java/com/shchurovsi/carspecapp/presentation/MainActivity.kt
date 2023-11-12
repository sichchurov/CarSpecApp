package com.shchurovsi.carspecapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shchurovsi.carspecapp.VehicleApplication
import com.shchurovsi.carspecapp.databinding.ActivityMainBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
