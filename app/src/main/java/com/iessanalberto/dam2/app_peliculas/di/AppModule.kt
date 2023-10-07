package com.iessanalberto.dam2.app_peliculas.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.iessanalberto.dam2.app_peliculas.network.ApiClient
import com.iessanalberto.dam2.app_peliculas.network.Network
import com.iessanalberto.dam2.app_peliculas.recursos.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
/*Esta clase nos sirve para inyectar dependencias sin obtener las fugas de memoria de "context"*/
//TODO Aun sin implementar las inyecciones a las vistas y fragmentos
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        //Interceptor web para debuggin de las peticiones
        val client = OkHttpClient.Builder()
        client.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })

        client.addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ):Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constantes.URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create(Network.moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiClient = retrofit.create(ApiClient::class.java)
}
