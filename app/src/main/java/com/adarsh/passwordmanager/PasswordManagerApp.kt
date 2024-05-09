package com.adarsh.passwordmanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class PasswordManagerApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}