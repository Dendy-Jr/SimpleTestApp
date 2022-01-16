package com.dendi.android.feelsoftwaretestapp.presentation.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dendi.android.feelsoftwaretestapp.R
import com.dendi.android.feelsoftwaretestapp.presentation.util.CarFilter
import com.dendi.android.feelsoftwaretestapp.core.OnClickListener
import com.dendi.android.feelsoftwaretestapp.databinding.FragmentMainBinding
import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car
import com.dendi.android.feelsoftwaretestapp.presentation.adapter.CarsAdapter
import com.dendi.android.feelsoftwaretestapp.presentation.viewmodel.CarsViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarsFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: CarsViewModel by viewModel()
    private val carsAdapter = CarsAdapter(object : OnClickListener<Car> {
        override fun click(item: Car) {
            showDetailsDialog(item)
        }
    })

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectLatestLifecycleFlow(viewModel.car) {
            carsAdapter.update(it)
        }
        setTimeWhenApiCalled()

        binding.btnShowMenu.setOnClickListener {
            showFilterDialog()
        }

        collectLatestLifecycleFlow(viewModel.readUserName()) { userName ->
            binding.tvHello.text = getString(R.string.hello_username, userName)
        }

        binding.tvLogOut.setOnClickListener {
            findNavController().popBackStack()
        }
        setupRecyclerView()
    }

    private fun setTimeWhenApiCalled() {
        collectLatestLifecycleFlow(viewModel.count) {
            binding.tvDataUpdated.text = getString(R.string.data_updated, it.toString())
            binding.tvDataUpdated.isVisible = true
        }
    }

    private fun setupRecyclerView() {
        binding.run {
            recyclerView.apply {
                adapter = carsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                itemAnimator?.changeDuration = 0
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
    }

    private fun showDetailsDialog(car: Car) {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_details, null)
        val title = view.findViewById<TextView>(R.id.tv_title)
        val state = view.findViewById<TextView>(R.id.tv_state)
        val date = view.findViewById<TextView>(R.id.text_view_date)
        val buttonClose = view.findViewById<MaterialButton>(R.id.bt_close)

        title.text = car.number.toString()
        state.text = getString(R.string.status, car.state)
        date.text = car.date

        buttonClose.setOnClickListener {
            dialog?.dismiss()
        }

        builder.setView(view)
        dialog = builder.create()
        dialog.show()
    }

    private fun showFilterDialog() {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_filter, null)
        val buttonNoFilter = view.findViewById<MaterialButton>(R.id.button_no_filter)
        val buttonAvailable = view.findViewById<MaterialButton>(R.id.button_available)
        val buttonHidden = view.findViewById<MaterialButton>(R.id.button_hidden)
        val buttonDisabled = view.findViewById<MaterialButton>(R.id.button_disabled)

        buttonNoFilter.setOnClickListener {
            carsAdapter.update(viewModel.car.value)
            dialog?.dismiss()
        }

        buttonAvailable.setOnClickListener {
            carsAdapter.update(viewModel.car.value.filter {
                it.state == CarFilter.AVAILABLE.state
            })
            dialog?.dismiss()
        }
        buttonHidden.setOnClickListener {
            carsAdapter.update(viewModel.car.value.filter {
                it.state == CarFilter.HIDDEN.state
            })
            dialog?.dismiss()
        }
        buttonDisabled.setOnClickListener {
            carsAdapter.update(viewModel.car.value.filter {
                it.state == CarFilter.DISABLED.state
            })
            dialog?.dismiss()
        }

        builder.setView(view)
        dialog = builder.create()
        dialog.show()
    }
}