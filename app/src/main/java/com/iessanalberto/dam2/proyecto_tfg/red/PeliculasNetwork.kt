package com.iessanalberto.dam2.proyecto_tfg.red

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.iessanalberto.dam2.proyecto_tfg.TFGApplication
import com.iessanalberto.dam2.proyecto_tfg.interfaces.ServicioMiApiPeliculas
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object PeliculasNetwork {

    //La instancia de Moshi se encarga de la serialización de los arhcivos JSON
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    //Retrofit se encargara de comunicarse con nuestra API
    val retrofit: Retrofit = Retrofit.Builder()
        .client(getLoggerHttpClient())
        .baseUrl(Constantes.URL_BASE)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /*Creamos la instancia de nuestro servicio usando retrofit para las llamadas de red,
    usamos metodos asíncronos onResponse y onFailure */

    val servicio: ServicioMiApiPeliculas by lazy {
        retrofit.create(ServicioMiApiPeliculas::class.java)
    }

    val clienteApi = ClienteApi(servicio)

    //Interceptor web para debuggin de las peticiones
    private fun getLoggerHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
        client.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })

        client.addInterceptor(
            ChuckerInterceptor.Builder(TFGApplication.context)
                .collector(ChuckerCollector(TFGApplication.context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()

        )
        return client.build()
    }
}