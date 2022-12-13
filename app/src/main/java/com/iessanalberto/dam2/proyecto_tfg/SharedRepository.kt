package com.iessanalberto.dam2.proyecto_tfg

import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieCreditsById

class SharedRepository {

    suspend fun getMovieById(movieId:Int): GetMovieById? {
        val peticion = Network.clienteApi.getMovieById(movieId)

        if (peticion.isSuccessful) {
            return peticion.body()!!
        }

        return null
    }

    suspend fun getMovieCreditsById(movieId:Int): GetMovieCreditsById? {
        val peticion = Network.clienteApi.getMovieCreditsById(movieId)

        if (peticion.isSuccessful) {
            return peticion.body()!!
        }

        return null
    }
}