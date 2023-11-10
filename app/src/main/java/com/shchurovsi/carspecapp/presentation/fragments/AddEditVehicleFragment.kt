package com.shchurovsi.carspecapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shchurovsi.carspecapp.R
import com.shchurovsi.carspecapp.databinding.FragmentAddEditVehicleBinding
import com.shchurovsi.carspecapp.domain.entities.Vehicle.Companion.UNDEFINED_ID
import com.shchurovsi.carspecapp.presentation.MainActivity
import com.shchurovsi.carspecapp.presentation.ViewModelFactory
import javax.inject.Inject

class AddEditVehicleFragment : Fragment() {

    private var _binding: FragmentAddEditVehicleBinding? = null
    private val binding: FragmentAddEditVehicleBinding
        get() = _binding ?: throw RuntimeException("ItemVehicleFragment is null!")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AddEditVehicleViewModel by viewModels { viewModelFactory }

    private var screenMode: String = UNDEFINED_EXTRA_VALUE
    private var vehicleItemId: Int = UNDEFINED_ID

    override fun onAttach(context: Context) {
        (activity as MainActivity).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchRightMode()
        observeViewModel()
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_ADD -> {
                launchAddMode()
            }

            MODE_EDIT -> {
                launchEditMode()
            }

            else -> {
                throw RuntimeException("Screen mode $screenMode doesn't exists")
            }
        }
    }

    private fun launchEditMode() {

        binding.btSave.text = getString(R.string.update)

        viewModel.getVehicleItem(vehicleItemId)

        binding.apply {
            viewModel.vehicleItem.observe(viewLifecycleOwner) {
                titBrand.setText(it.brand)
                titMotorPower.setText(it.motorPower)
                titSeats.setText(it.seats)
            }

            btSave.setOnClickListener {
                viewModel.editVehicle(
                    titBrand.text.toString(),
                    titMotorPower.text.toString(),
                    titSeats.text.toString(),
                    ""
                )
            }

            btCancel.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun launchAddMode() {
        binding.apply {
            btSave.setOnClickListener {
                viewModel.addVehicle(
                    titBrand.text.toString(),
                    titMotorPower.text.toString(),
                    titSeats.text.toString(),
                    ""
                )
            }

            btCancel.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is State.InputBrandError -> {
                    val message = if (it.state) {
                        getString(R.string.empty_field_error, "${binding.titBrand.hint}")
                    } else null

                    binding.titBrand.error = message
                }

                is State.InputMotorPowerError -> {
                    val messages = if (it.state) {
                        getString(R.string.empty_field_error, "${binding.titMotorPower.hint}")
                    } else null

                    binding.titMotorPower.error = messages
                }

                is State.InputSeatsError -> {
                    val messages = if (it.state) {
                        getString(R.string.empty_field_error, "${binding.titSeats.hint}")
                    } else null

                    binding.titSeats.error = messages
                }

                is State.ShouldCloseScreenState -> {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }

    private fun parseParams() {
        val args = requireArguments()

        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Screen mode doesn't exists")
        }

        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Screen mode $mode doesn't exists")
        }

        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(VEHICLE_ITEM_ID)) {
                throw RuntimeException("Param item id doesn't exists")
            }

            vehicleItemId = args.getInt(VEHICLE_ITEM_ID)
        }
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val VEHICLE_ITEM_ID = "extra_todo_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val UNDEFINED_EXTRA_VALUE = ""

        fun newInstanceAddItemVehicleFragment(): AddEditVehicleFragment {
            return AddEditVehicleFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItemVehicleFragment(vehicleItemId: Int): AddEditVehicleFragment {
            return AddEditVehicleFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(VEHICLE_ITEM_ID, vehicleItemId)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
