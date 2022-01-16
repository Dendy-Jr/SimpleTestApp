package com.dendi.android.feelsoftwaretestapp.domain.cars.repository

import com.dendi.android.feelsoftwaretestapp.data.cars.CarsCloudDataSource
import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car
import com.dendi.android.feelsoftwaretestapp.domain.cars.mapper.CarCloudToCarMapper

interface CarsRepository {

    suspend fun fetchCars(): List<Car>

    class CarsRepositoryImpl(
        private val cloudDataSource: CarsCloudDataSource,
        private val mapper: CarCloudToCarMapper
    ) : CarsRepository {
        override suspend fun fetchCars(): List<Car> {
            return cloudDataSource.fetchCars().cars.map { carCloud ->
                mapper.map(carCloud)
            }
        }
    }
}