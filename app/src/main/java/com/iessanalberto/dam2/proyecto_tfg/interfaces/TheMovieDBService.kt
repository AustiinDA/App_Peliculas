package com.iessanalberto.dam2.proyecto_tfg.interfaces

import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieById
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface TheMovieDBService {

    //@GET("/3/movie/top_rated?api_key=79d75b7c06a6ad8417f82d9e66367c94&language=es-ES&page=1")
    @GET("/3/movie/{id-pelicula}?api_key=79d75b7c06a6ad8417f82d9e66367c94&language=es-ES")
    fun getMovieById(
        @Path("id-pelicula") id: Int
    ): Call<GetMovieById>

    @GET("https://image.tmdb.org/t/p/w500/{movie-poster}")
    fun getMovieImage(
        @Path("movie-poster") poster: String
    ): Call<GetMovieById>
}