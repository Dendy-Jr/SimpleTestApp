package com.dendi.android.feelsoftwaretestapp.domain.cars.usecase

import com.dendi.android.feelsoftwaretestapp.presentation.util.CarFilter
import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car
import com.dendi.android.feelsoftwaretestapp.domain.cars.repository.CarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilterCarsUseCase(private val repository: CarsRepository) {

    suspend fun filterCars(filter: CarFilter): List<Car>  = withContext(Dispatchers.Default){
        val cars = repository.fetchCars()
        return@withContext when (filter) {
            filter -> cars.filter {
                it.state == filter.state
            }
            else -> emptyList()
        }
    }
}