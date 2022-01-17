package com.dendi.android.feelsoftwaretestapp.core

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {

    fun provideString(@StringRes id: Int) : String

    class ResourceProviderImpl(private val context: Context): ResourceProvider {
        override fun provideString(id: Int) = context.getString(id)
    }
}