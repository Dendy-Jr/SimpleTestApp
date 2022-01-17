package com.dendi.android.feelsoftwaretestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dendi.android.feelsoftwaretestapp.core.ExceptionMapper
import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car
import com.dendi.android.feelsoftwaretestapp.domain.cars.usecase.GetCarsUseCase
import com.dendi.android.feelsoftwaretestapp.domain.user.repository.UserPreferencesRepository
import com.dendi.android.feelsoftwaretestapp.presentation.util.NetworkResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class CarsViewModel(
    private val repository: UserPreferencesRepository,
    private val getCarsUseCase: GetCarsUseCase,
    private val mapper: ExceptionMapper
) : ViewModel() {

    private val _cars = MutableStateFlow<NetworkResult<List<Car>>>(NetworkResult.Loading())
    val car: StateFlow<NetworkResult<List<Car>>> = _cars.asStateFlow()

    private val _counter = MutableStateFlow<Int>(0)
    val count: StateFlow<Int> = _counter.asStateFlow()

    init {
        getCarsSafeCall()
        latestCallTime()
    }

    fun readUserName() = repository.readName()

    private fun getCarsSafeCall() {
        viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                _cars.value = NetworkResult.Loading()
                try {
                    val response = getCarsUseCase.fetchCars()
                    _cars.value = NetworkResult.Success(response)
                    delay(10000)
                } catch (e: Exception) {
                    _cars.value = NetworkResult.Error(e.message)
                }
            }
        }
    }

    private fun latestCallTime() {
        viewModelScope.launch(Dispatchers.IO) {
            var timer = 0
            while (isActive) {
                delay(1000)
                timer++
                if (timer > 10) timer = 1
                _counter.value = timer
            }
        }
    }
}
