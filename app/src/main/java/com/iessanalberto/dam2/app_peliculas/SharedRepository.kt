package com.iessanalberto.dam2.app_peliculas

import com.iessanalberto.dam2.app_peliculas.dominio.mapeadores.CreditsMapper
import com.iessanalberto.dam2.app_peliculas.dominio.mapeadores.MovieMapper
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Credits
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Movie
import com.iessanalberto.dam2.app_peliculas.network.Network

class SharedRepository {

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


}