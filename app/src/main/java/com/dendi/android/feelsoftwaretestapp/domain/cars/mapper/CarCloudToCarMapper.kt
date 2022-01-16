package com.dendi.android.feelsoftwaretestapp.domain.cars.mapper

import com.dendi.android.feelsoftwaretestapp.core.Mapper
import com.dendi.android.feelsoftwaretestapp.data.cars.CarCloud
import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car
import java.text.SimpleDateFormat
import java.util.*

class CarCloudToCarMapper : Mapper<CarCloud, Car> {
    override fun map(item: CarCloud) =
        Car(
            date = stringToDate(item.date),
            number = item.number,
            state = item.state,
        )

    private fun stringToDate(date: String): String {
        val dateInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val dateOutput = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val newDate = dateInput.parse(date)
        return dateOutput.format(newDate)
    }
}
