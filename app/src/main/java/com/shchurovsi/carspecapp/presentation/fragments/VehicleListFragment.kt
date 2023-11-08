package com.shchurovsi.carspecapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shchurovsi.carspecapp.databinding.FragmentItemVehicleBinding
import com.shchurovsi.carspecapp.databinding.FragmentVehicleListBinding
import com.shchurovsi.carspecapp.presentation.MainActivity
import com.shchurovsi.carspecapp.presentation.ViewModelFactory
import javax.inject.Inject

class VehicleListFragment : Fragment() {

    private var _binding: FragmentVehicleListBinding? = null
    private val binding: FragmentVehicleListBinding
        get() = _binding ?: throw RuntimeException("ItemVehicleFragment is null!")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: VehicleListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        (activity as MainActivity).appComponent.inject(this)
        super.onAttach(context)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
