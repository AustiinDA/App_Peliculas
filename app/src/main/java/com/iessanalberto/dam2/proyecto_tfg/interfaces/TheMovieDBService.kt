package com.iessanalberto.dam2.proyecto_tfg.interfaces

import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.*
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

    @GET("/3/movie/popular/")
    suspend fun getPopularMoviesPage(
        @Query("page") pageIndex: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<GetPopularMovies>

    //Lista paginada de pelis
    @GET("/3/discover/movie")
    suspend fun getMovieDiscoveryPage(
        @Query("page") pageIndex: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<GetMovieDiscoveryPage>

    //Resultados con sus ids y caracteristicas
    @GET("/3/discover/movie")
    suspend fun getMovieDiscoveryById(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<GetMovieDiscoveryById>


    @GET("/3/search/movie")
    suspend fun getMovieSearchPage(
        @Query("page") pageIndex: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") movieTitle: String
    ): Response<GetMovieSearchPage>

//    @GET("/3/discover/movie")
//    suspend fun getMovieSearchByTitle(
//        @Query("title") movieTitle: String,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): Response<GetMovieSearchByQuery>
}

