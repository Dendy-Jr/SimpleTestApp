package com.dendi.android.feelsoftwaretestapp.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

interface PreferenceProvider {

    fun provideDataStore(name: String): DataStore<Preferences>

    class PreferenceProviderImpl(private val context: Context) : PreferenceProvider {

        private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

        override fun provideDataStore(name: String): DataStore<Preferences> = context.dataStore

        private companion object {
            const val PREFERENCES_NAME = "PREFERENCES_NAME"
        }
    }
}