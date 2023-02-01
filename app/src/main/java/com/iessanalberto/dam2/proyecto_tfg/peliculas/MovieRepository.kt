package com.iessanalberto.dam2.proyecto_tfg.peliculas

import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.DiscoverMapper
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MovieMapper
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Discover
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Movie
import com.iessanalberto.dam2.proyecto_tfg.network.Network
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieDiscoveryById
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieDiscoveryPage

class MovieRepository {
    suspend fun fetchMoviePage(pageIndex: Int): GetMovieDiscoveryPage? {
        val pagina = Network.clienteApi.getMovieDiscoveryPage(pageIndex)
        if (!pagina.isSuccessful) {
            return null
        }

        return pagina.body
    }

    suspend fun getMovieDiscovery(): Discover? {
        val peticion = Network.clienteApi.getMovieDiscoveryById()
        if (!peticion.isSuccessful){
            return null
        }

        return DiscoverMapper.buildOf(
            respuesta = peticion.body
        )
    }

    suspend fun getMovieById(movieId: Int): Movie? {
        val peticion = Network.clienteApi.getMovieById(movieId)

        //Recibimos una llamada a la Api, pero en vez de crashear la app, devolvemos un estado de fallo
        if (peticion.failed) {
            return null
        }

        if (!peticion.isSuccessful) {
            return null
        }

        return MovieMapper.buildOf(
            respuesta = peticion.body,
            genres = peticion.body.genres
        )
    }
//    private suspend fun getMoviesFromDiscovery(
//        movieById: GetMovieById
//    ):List<GetMovieDiscoveryById>{
//        val movieList = movieById.
//    }

}