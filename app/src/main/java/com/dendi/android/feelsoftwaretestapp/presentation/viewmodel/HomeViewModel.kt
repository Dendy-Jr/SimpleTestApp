package com.dendi.android.feelsoftwaretestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dendi.android.feelsoftwaretestapp.domain.user.repository.UserPreferencesRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

    fun saveUserName(name: String) {
        viewModelScope.launch {
            repository.saveUserName(name)
        }
    }
}