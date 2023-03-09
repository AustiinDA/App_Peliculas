package com.iessanalberto.dam2.proyecto_tfg.database

import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class RealmApplication: Application() {


    private var config: RealmConfiguration? = null

    override fun onCreate() {
        super.onCreate()

    }

}