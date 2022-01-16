package com.dendi.android.feelsoftwaretestapp.domain.cars.usecase

import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car
import com.dendi.android.feelsoftwaretestapp.domain.cars.repository.CarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCarsUseCase(private val repository: CarsRepository) {

    suspend fun fetchCars(): List<Car> = withContext(Dispatchers.IO) {
        repository.fetchCars()
    }
}