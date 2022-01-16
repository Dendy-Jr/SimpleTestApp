package com.dendi.android.feelsoftwaretestapp.di

import com.dendi.android.feelsoftwaretestapp.domain.cars.usecase.FilterCarsUseCase
import com.dendi.android.feelsoftwaretestapp.domain.cars.usecase.GetCarsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetCarsUseCase> { GetCarsUseCase(repository = get()) }

    factory<FilterCarsUseCase> { FilterCarsUseCase(repository = get()) }
}