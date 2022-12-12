package com.iessanalberto.dam2.proyecto_tfg

import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieCreditsById
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes

class SharedRepository {

    suspend fun getMovieById(movieId:Int): GetMovieById? {
        val peticion = Network.clienteApi.getMovieById(564)

        if (peticion.isSuccessful) {
            return peticion.body()!!
        }

        return null
    }

//    suspend fun getMovieCreditsById(movieId:Int): GetMovieCreditsById? {
//        val peticion = Network.clienteApi.getMovieCreditsById(564)
//
//        if (peticion.isSuccessful) {
//            return peticion.body()!!
//        }
//
//        return null
//    }
}