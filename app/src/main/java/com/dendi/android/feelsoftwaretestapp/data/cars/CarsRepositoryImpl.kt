package com.dendi.android.feelsoftwaretestapp.data.cars

import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car
import com.dendi.android.feelsoftwaretestapp.domain.cars.mapper.CarCloudToCarMapper
import com.dendi.android.feelsoftwaretestapp.domain.cars.repository.CarsRepository


class CarsRepositoryImpl(
    private val cloudDataSource: CarsCloudDataSource,
    private val mapper: CarCloudToCarMapper
) : CarsRepository {
    override suspend fun fetchCars(): List<Car> =
        cloudDataSource.fetchCars().cars.map { carCloud ->
            mapper.map(carCloud)
        }
}