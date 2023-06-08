package com.iessanalberto.dam2.proyecto_tfg.interfaces

import com.iessanalberto.dam2.proyecto_tfg.red.respuestas.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/*La interfaz de servicio será nuestro intermediario para las llamadas en red, retrofit accederá
* a esta interfaz para obtener las llamadas que hacemos a nuestra API a través de las antotaciones*/
interface ServicioMiApiPeliculas {
    //El modificador 'suspend' se usara para las llamadas y corrutinas
    @GET("/peliculas/{id_pelicula}")
    suspend fun getPeliculaPorId(
        @Path("id_pelicula") id: String
    ): Response<GetPeliculaPorId>

    @GET("/peliculas/descubrir")
    suspend fun getDescubrirPeliculas(): Response<GetDevolverPeliculas>

    @GET("/peliculas/descubrir")
    suspend fun getDescubrirPeliculasPaginadas(
        @Query("pagina") numPagina: Int
    ): Response<GetDevolverPeliculasPaginadas>


    @GET("/peliculas/{idPelicula}/creditos")
    suspend fun getCreditosPorPeliculaId(
        @Path("idPelicula") id: String
    ): Response<GetDevolverCreditosPorIdPelicula>

    @GET("peliculas/descubrir")
    suspend fun getBusquedaPeliculasPaginadas(
        @Query("pagina") pageIndex: Int,
        @Query("titulo") tituloPelicula: String
    ): Response<GetDevolverPeliculasPaginadas>


}

