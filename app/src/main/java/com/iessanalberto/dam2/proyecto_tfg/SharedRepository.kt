package com.iessanalberto.dam2.proyecto_tfg

import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieCreditsById
import com.iessanalberto.dam2.proyecto_tfg.network.Network

class SharedRepository {

    suspend fun getMovieById(movieId:Int): GetMovieById? {
        val peticion = Network.clienteApi.getMovieById(movieId)

        //Recibimos una llamada a la Api, pero en vez de crashear la app, devolvemos un estado de fallo
        if (peticion.failed){
            return null
        }

        if (!peticion.isSuccessful) {
            return null
        }

        return peticion.body
    }

    suspend fun getMovieCreditsById(movieId:Int): GetMovieCreditsById? {
        val peticion = Network.clienteApi.getMovieCreditsById(movieId)

        if (peticion.failed){
            return null
        }

        if (!peticion.isSuccessful) {
            return null
        }

        return peticion.body
    }
}