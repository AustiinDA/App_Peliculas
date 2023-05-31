package com.iessanalberto.dam2.proyecto_tfg.interfaces

import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMovieDBService {
    //El modificador 'suspend' se usara para las llamadas y corrutinas
    @GET("/peliculas/{id_pelicula}")
    suspend fun getPeliculaPorId(
        @Path("id_pelicula") id: String
    ): Response<GetPeliculaPorId>

    @GET("/peliculas/descubrir")
    suspend fun getDescubrirPeliculas(): Response<GetDevolverTodasPeliculas>

    @GET("/peliculas/descubrir")
    suspend fun getDescubrirPeliculasPaginadas(
        @Query("page") numPagina: Int
    ): Response<GetDevolverTodasPeliculasPaginado>


    @GET("/peliculas/{idPelicula}/creditos")
    suspend fun getCreditosPorPeliculaId(
        @Path("idPelicula") id: String
    ): Response<GetDevolverCreditosPorIdPelicula>

//    @GET("/3/movie/{id-pelicula}/credits")
//    suspend fun getMovieCreditsById(
//        @Path("id-pelicula") id: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): Response<GetMovieCreditsById>
//
//    @GET("/3/discover/movie")
//    suspend fun getMovieDiscoveryById(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): Response<GetMovieDiscoveryById>
    @GET("peliculas/descubrir")
    suspend fun getBusquedaPeliculasPaginadas(
        @Query("page") pageIndex: Int,
        @Query("titulo") tituloPelicula: String
    ): Response<GetDevolverTodasPeliculasPaginado>

//    @GET("/3/search/movie")
//    suspend fun getMovieSearchPage(
//        @Query("page") pageIndex: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("query") movieTitle: String
//    ): Response<GetMovieSearchPage>
//
//    @GET("/3/person/{person_id}")
//    suspend fun getPersonById(
//        @Path("person_id") id: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): Response<GetPersonById>

}

