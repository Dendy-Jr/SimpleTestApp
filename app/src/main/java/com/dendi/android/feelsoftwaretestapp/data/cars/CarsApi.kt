package com.dendi.android.feelsoftwaretestapp.data.cars

import retrofit2.http.GET

interface CarsApi {

    @GET("jsonplaceholder/cars-api.php")
    suspend fun fetchCars(): CarsResponse
}