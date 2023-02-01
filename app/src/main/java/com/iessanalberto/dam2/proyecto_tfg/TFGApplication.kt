package com.iessanalberto.dam2.proyecto_tfg

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TFGApplication: Application() {
    //TODO no usar el context de Application debido a fuga de memoria, usar inyeccion Hilt
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}