package com.hs.githubexercise

import android.app.Application
import com.hs.githubexercise.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(repoModule, viewModelModule, fragmentModule, apiModule, netModule))
        }
    }
}