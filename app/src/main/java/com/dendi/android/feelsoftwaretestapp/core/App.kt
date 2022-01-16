package com.dendi.android.feelsoftwaretestapp.core

import android.app.Application
import com.dendi.android.feelsoftwaretestapp.BuildConfig
import com.dendi.android.feelsoftwaretestapp.di.appModule
import com.dendi.android.feelsoftwaretestapp.di.dataModule
import com.dendi.android.feelsoftwaretestapp.di.domainModule
import com.dendi.android.feelsoftwaretestapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule, networkModule))
        }
    }
}