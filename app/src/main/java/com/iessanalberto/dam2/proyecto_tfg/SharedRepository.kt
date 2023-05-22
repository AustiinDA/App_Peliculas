package com.iessanalberto.dam2.proyecto_tfg

import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorPelicula
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula
import com.iessanalberto.dam2.proyecto_tfg.network.Network

class SharedRepository {

    suspend fun getPeliculasPorId(idPelicula: String): Pelicula? {
        val peticion = Network.clienteApi.getPeliculaPorId(idPelicula)

        //Recibimos una llamada a la Api, pero en vez de crashear la app, devolvemos un estado de fallo
        if (peticion.failed) {
            return null
        }

        if (!peticion.isSuccessful) {
            return null
        }

        return MapeadorPelicula.construirDe(
            respuesta = peticion.body
        )
    }

//    suspend fun getMovieCreditsById(movieId: Int): Credits? {
//        val peticion = Network.clienteApi.getMovieCreditsById(movieId)
//
//        if (peticion.failed) {
//            return null
//        }
//
//        if (!peticion.isSuccessful) {
//            return null
//        }
//
//        return CreditsMapper.buildOf(
//            respuesta = peticion.body,
//            crew = peticion.body.crew,
//            cast = peticion.body.cast
//        )
//    }


}