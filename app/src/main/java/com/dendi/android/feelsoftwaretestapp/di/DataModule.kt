package com.dendi.android.feelsoftwaretestapp.di

import com.dendi.android.feelsoftwaretestapp.core.ExceptionMapper
import com.dendi.android.feelsoftwaretestapp.core.PreferenceProvider
import com.dendi.android.feelsoftwaretestapp.core.ResourceProvider
import com.dendi.android.feelsoftwaretestapp.data.cars.CarsCloudDataSource
import com.dendi.android.feelsoftwaretestapp.data.cars.CarsRepositoryImpl
import com.dendi.android.feelsoftwaretestapp.data.user.UserPreferencesRepositoryImpl
import com.dendi.android.feelsoftwaretestapp.domain.cars.mapper.CarCloudToCarMapper
import com.dendi.android.feelsoftwaretestapp.domain.cars.repository.CarsRepository
import com.dendi.android.feelsoftwaretestapp.domain.user.repository.UserPreferencesRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(preferenceProvider = get()) }

    single<CarsRepository> {
        CarsRepositoryImpl(
            cloudDataSource = get(),
            mapper = get()
        )
    }

    factory<ExceptionMapper> { ExceptionMapper.ExceptionMapperImpl(resourceProvider = get()) }

    factory<ResourceProvider> { ResourceProvider.ResourceProviderImpl(context = androidApplication()) }

    factory<CarsCloudDataSource> { CarsCloudDataSource.CarsCloudDataSourceImpl(api = get()) }

    factory { CarCloudToCarMapper() }

    factory<PreferenceProvider> { PreferenceProvider.PreferenceProviderImpl(context = androidContext()) }
}