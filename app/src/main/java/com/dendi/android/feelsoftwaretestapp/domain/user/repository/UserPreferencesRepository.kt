package com.dendi.android.feelsoftwaretestapp.domain.user.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    suspend fun saveUserName(name: String)

    fun readName(): Flow<String>
}