package com.dendi.android.feelsoftwaretestapp.di

import com.dendi.android.feelsoftwaretestapp.core.PreferenceProvider
import com.dendi.android.feelsoftwaretestapp.data.cars.CarsCloudDataSource
import com.dendi.android.feelsoftwaretestapp.data.user.UserPreferencesRepositoryImpl
import com.dendi.android.feelsoftwaretestapp.domain.cars.mapper.CarCloudToCarMapper
import com.dendi.android.feelsoftwaretestapp.domain.cars.repository.CarsRepository
import com.dendi.android.feelsoftwaretestapp.domain.user.repository.UserPreferencesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(preferenceProvider = get()) }

    single<CarsRepository> {
        CarsRepository.CarsRepositoryImpl(
            cloudDataSource = get(),
            mapper = get()
        )
    }

    factory<CarsCloudDataSource> { CarsCloudDataSource.CarsCloudDataSourceImpl(service = get()) }

    factory { CarCloudToCarMapper() }

    factory<PreferenceProvider> { PreferenceProvider.PreferenceProviderImpl(context = androidContext()) }
}