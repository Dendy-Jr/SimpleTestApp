package com.dendi.android.feelsoftwaretestapp.data.cars


interface CarsCloudDataSource {

    suspend fun fetchCars(): CarsResponse

    class CarsCloudDataSourceImpl(private val service: CarsService) : CarsCloudDataSource {
        override suspend fun fetchCars(): CarsResponse =
            service.fetchCars()
    }
}