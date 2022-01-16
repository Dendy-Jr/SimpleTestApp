package com.dendi.android.feelsoftwaretestapp.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dendi.android.feelsoftwaretestapp.R
import com.dendi.android.feelsoftwaretestapp.core.OnClickListener
import com.dendi.android.feelsoftwaretestapp.databinding.CarItemBinding
import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car
import com.dendi.android.feelsoftwaretestapp.presentation.util.CarFilter

class CarViewHolder(
    private val binding: CarItemBinding,
    private val listener: OnClickListener<Car>
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(car: Car) {
        binding.run {
            tvDate.text = car.date
            tvNumber.text = car.number.toString()
            frameLayout.setBackgroundResource(
                when (car.state) {
                    CarFilter.AVAILABLE.state -> CarFilter.AVAILABLE.color
                    CarFilter.HIDDEN.state -> CarFilter.HIDDEN.color
                    CarFilter.DISABLED.state -> CarFilter.DISABLED.color
                    else -> R.color.white
                }
            )
        }
        itemView.setOnClickListener {
            listener.click(car)
        }
    }
}