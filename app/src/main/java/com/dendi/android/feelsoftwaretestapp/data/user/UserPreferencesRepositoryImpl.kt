package com.dendi.android.feelsoftwaretestapp.data.user

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dendi.android.feelsoftwaretestapp.core.PreferenceProvider
import com.dendi.android.feelsoftwaretestapp.data.user.UserPreferencesRepositoryImpl.PreferenceKey.USER_NAME
import com.dendi.android.feelsoftwaretestapp.domain.user.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

class UserPreferencesRepositoryImpl(
    preferenceProvider: PreferenceProvider
) : UserPreferencesRepository {

    private val dataStore: DataStore<Preferences> =
        preferenceProvider.provideDataStore(PREFERENCES_NAME)

    private object PreferenceKey {
        val USER_NAME = stringPreferencesKey(USER_NAME_KEY)
    }

    override suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.USER_NAME] = name
        }
    }

    override fun readName(): Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                exception.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[USER_NAME] ?: ""
        }

    private companion object {
        const val USER_NAME_KEY = "user_name_key"
        const val PREFERENCES_NAME = "PREFERENCES_NAME"
    }
}