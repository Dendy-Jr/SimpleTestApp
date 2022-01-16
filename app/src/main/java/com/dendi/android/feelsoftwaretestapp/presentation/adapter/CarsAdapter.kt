package com.dendi.android.feelsoftwaretestapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dendi.android.feelsoftwaretestapp.core.OnClickListener
import com.dendi.android.feelsoftwaretestapp.databinding.CarItemBinding
import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car

class CarsAdapter(private val listener: OnClickListener<Car>) :
    RecyclerView.Adapter<CarViewHolder>() {

    private val carsList = ArrayList<Car>()

    fun update(data: List<Car>) {
        val diffCallback = DiffUtilCallback<Car>(carsList, data)
        val result = DiffUtil.calculateDiff(diffCallback)
        carsList.clear()
        carsList.addAll(data)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carsList[position])
    }

    override fun getItemCount() = carsList.size
}