package com.iessanalberto.dam2.proyecto_tfg.interfaces

import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieCreditsById
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMovieDBService {
    //El modificador 'suspend' se usara para las llamadas y corrutinas
    @GET("/3/movie/{id_pelicula}")
    suspend fun getMovieById(
        @Path("id_pelicula") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<GetMovieById>

    @GET("/3/movie/{id-pelicula}/credits")
    suspend fun getMovieCreditsById(
        @Path("id-pelicula") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<GetMovieCreditsById>
}