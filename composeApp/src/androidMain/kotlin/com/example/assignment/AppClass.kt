package com.example.assignment

import android.app.Application

class AppClass:  Application() {

    companion object {
        lateinit var instance: AppClass
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}