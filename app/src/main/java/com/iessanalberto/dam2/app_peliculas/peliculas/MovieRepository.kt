package com.iessanalberto.dam2.app_peliculas.peliculas

import com.iessanalberto.dam2.app_peliculas.dominio.mapeadores.CreditsMapper
import com.iessanalberto.dam2.app_peliculas.dominio.mapeadores.DiscoverMapper
import com.iessanalberto.dam2.app_peliculas.dominio.mapeadores.MovieMapper
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Credits
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Discover
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Movie
import com.iessanalberto.dam2.app_peliculas.network.Network
import com.iessanalberto.dam2.app_peliculas.network.respuestas.GetMovieDiscoveryPage

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
            respuesta = peticion.body
        )
    }

    suspend fun getMovieCreditsById(movieId: Int): Credits? {
        val peticion = Network.clienteApi.getMovieCreditsById(movieId)

        if (peticion.failed) {
            return null
        }

        if (!peticion.isSuccessful) {
            return null
        }

        return CreditsMapper.buildOf(
            respuesta = peticion.body,
            crew = peticion.body.crew,
            cast = peticion.body.cast
        )
    }

//    private suspend fun getMoviesFromDiscovery(
//        movieById: GetMovieById
//    ):List<GetMovieDiscoveryById>{
//        val movieList = movieById.
//    }

}