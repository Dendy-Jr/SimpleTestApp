package com.dendi.android.feelsoftwaretestapp.di

import com.dendi.android.feelsoftwaretestapp.presentation.viewmodel.CarsViewModel
import com.dendi.android.feelsoftwaretestapp.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel<HomeViewModel> { HomeViewModel(repository = get()) }

    viewModel<CarsViewModel> { CarsViewModel(repository = get(), getCarsUseCase = get(), mapper = get() ) }
}