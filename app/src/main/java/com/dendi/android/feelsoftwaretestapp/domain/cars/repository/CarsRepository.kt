package com.dendi.android.feelsoftwaretestapp.domain.cars.repository

import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car

interface CarsRepository {

    suspend fun fetchCars(): List<Car>
}