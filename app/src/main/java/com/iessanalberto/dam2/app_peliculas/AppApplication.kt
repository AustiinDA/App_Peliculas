package com.iessanalberto.dam2.app_peliculas

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication: Application() {
    //TODO no usar el context de Application debido a fuga de memoria, usar inyeccion Hilt
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}