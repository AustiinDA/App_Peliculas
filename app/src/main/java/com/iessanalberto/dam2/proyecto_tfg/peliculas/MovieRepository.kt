package com.iessanalberto.dam2.proyecto_tfg.peliculas

import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetPopularMovies
import com.iessanalberto.dam2.proyecto_tfg.network.Network
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieDiscovery

class MovieRepository {
    suspend fun fetchMoviePage(pageIndex: Int): GetMovieDiscovery? {
        val peticion = Network.clienteApi.getMovieDiscoveryPage(pageIndex)
        if (!peticion.isSuccessful) {
            return null
        }

        return peticion.body
    }
}