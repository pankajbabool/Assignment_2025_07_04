package com.example.assignment

import android.app.Application
import com.example.assignment.di.initKoin
import org.koin.android.ext.koin.androidContext

class AppClass:  Application() {

    companion object {
        lateinit var instance: AppClass
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        initKoin {
            androidContext(this@AppClass)
        }
    }
}