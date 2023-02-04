package com.iessanalberto.dam2.proyecto_tfg.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.iessanalberto.dam2.proyecto_tfg.TFGApplication
import com.iessanalberto.dam2.proyecto_tfg.interfaces.TheMovieDBService
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    //Moshi se encarga de la serialización de los arhcivos JSON
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    val retrofit: Retrofit = Retrofit.Builder()
        .client(getLoggerHttpClient())
        .baseUrl(Constantes.URL_BASE)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /*Creamos la instancia de nuestra interfaz usando retrofit para las llamadas de red,
    usamos metodos asíncronos onResponse y onFailure */

    val tmdbService: TheMovieDBService by lazy {
        retrofit.create(TheMovieDBService::class.java)
    }

    val clienteApi = ApiClient(tmdbService)

    //Interceptor web para debuggin de las peticiones
    private fun getLoggerHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
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