package com.example.tmdbapp

import android.app.Application
import timber.log.Timber

class TMDBapp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}