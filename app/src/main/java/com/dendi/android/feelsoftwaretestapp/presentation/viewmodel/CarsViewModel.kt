package com.dendi.android.feelsoftwaretestapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dendi.android.feelsoftwaretestapp.domain.cars.entity.Car
import com.dendi.android.feelsoftwaretestapp.domain.cars.usecase.GetCarsUseCase
import com.dendi.android.feelsoftwaretestapp.domain.user.repository.UserPreferencesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class CarsViewModel(
    private val repository: UserPreferencesRepository,
    private val getCarsUseCase: GetCarsUseCase,
) : ViewModel() {

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val car: StateFlow<List<Car>> = _cars.asStateFlow()

    private val _counter = MutableStateFlow<Int>(0)
    val count: StateFlow<Int> = _counter.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                _cars.value = getCarsUseCase.fetchCars()
                delay(10000)
                Log.d("CARS", "UI updated")
            }
        }

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

    fun readUserName() = repository.readName()
}
