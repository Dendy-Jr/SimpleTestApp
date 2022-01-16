package com.dendi.android.feelsoftwaretestapp.di

import com.dendi.android.feelsoftwaretestapp.BuildConfig
import com.dendi.android.feelsoftwaretestapp.data.cars.CarsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    factory<GsonConverterFactory> { GsonConverterFactory.create() }

    fun getRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    factory { getRetrofit(client = get(), gsonConverterFactory = get()) }

    fun getCarsService(retrofit: Retrofit): CarsService {
        return retrofit.create(CarsService::class.java)
    }

    factory { getCarsService(retrofit = get()) }
}