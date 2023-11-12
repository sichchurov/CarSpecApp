package com.shchurovsi.carspecapp.presentation.fragments

import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shchurovsi.carspecapp.R
import com.shchurovsi.carspecapp.databinding.FragmentVehicleListBinding
import com.shchurovsi.carspecapp.databinding.FullImageLayoutBinding
import com.shchurovsi.carspecapp.domain.entities.Vehicle
import com.shchurovsi.carspecapp.presentation.MainActivity
import com.shchurovsi.carspecapp.presentation.ViewModelFactory
import com.shchurovsi.carspecapp.presentation.vehicleadapter.VehicleAdapter
import javax.inject.Inject
import kotlin.math.roundToInt

class VehicleListFragment : Fragment() {

    private var _binding: FragmentVehicleListBinding? = null

    private val binding: FragmentVehicleListBinding
        get() = _binding ?: throw RuntimeException("ItemVehicleFragment is null!")

    private val vehicleAdapter by lazy {
        VehicleAdapter()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: VehicleListViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        (activity as MainActivity).appComponent.inject(this)
        super.onAttach(context)
    }

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
        setupRecycler()
        observe()
        launchFragment()
        vehicleAdapter.setOnItemClickListener { vehicle ->
            showBrandImageDialog(vehicle)
        }

        setupSwipeListener()
    }

    private fun setupSwipeListener() {
        val editIcon =
            ResourcesCompat.getDrawable(requireContext().resources, R.drawable.ic_edit, null)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.layoutPosition
                val vehicleItem = vehicleAdapter.currentList[position]
                requireActivity().apply {
                    supportFragmentManager.popBackStack()
                    supportFragmentManager.commit {
                        replace(
                            R.id.fragment_container_view,
                            AddEditVehicleFragment.newInstanceEditItemVehicleFragment(vehicleItem.id)
                        )
                        addToBackStack(null)
                    }
                }
            }

            override fun onChildDraw(
                canvas: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val textMargin = resources.getDimension(R.dimen.text_margin)
                    .roundToInt()
                editIcon?.let {
                    it.bounds = Rect(
                        textMargin,
                        viewHolder.itemView.top + textMargin + 16.dp,
                        textMargin + it.intrinsicWidth,
                        viewHolder.itemView.top + it.intrinsicHeight
                                + textMargin + 16.dp
                    )
                }

                if (dX > 0) editIcon?.draw(canvas)

                super.onChildDraw(
                    canvas,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

        }).attachToRecyclerView(binding.recycler)
    }

    private fun showBrandImageDialog(vehicle: Vehicle) {
        val binding = FullImageLayoutBinding.inflate(layoutInflater)

        AlertDialog.Builder(requireContext()).apply {
            setView(binding.root)
            Glide.with(this@VehicleListFragment)
                .load(Uri.parse(vehicle.image))
                .into(binding.vehicleFullImage)
            show()
        }
    }

    private fun observe() {
        viewModel.vehicleList.observe(viewLifecycleOwner) {
            vehicleAdapter.submitList(it)
        }
    }

    private val Int.dp
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            toFloat(), resources.displayMetrics
        ).roundToInt()

    private fun launchFragment() {
        binding.myFAB.setOnClickListener {
            requireActivity().apply {
                supportFragmentManager.popBackStack()
                supportFragmentManager.commit {
                    replace(
                        R.id.fragment_container_view,
                        AddEditVehicleFragment.newInstanceAddItemVehicleFragment()
                    )
                    addToBackStack(null)
                }
            }
        }
    }

    private fun setupRecycler() {
        binding.recycler.adapter = vehicleAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
