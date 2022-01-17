package com.dendi.android.feelsoftwaretestapp.data.cars


interface CarsCloudDataSource {

    suspend fun fetchCars(): CarsResponse

    class CarsCloudDataSourceImpl(private val api: CarsApi) : CarsCloudDataSource {
        override suspend fun fetchCars(): CarsResponse = api.fetchCars()
    }
}