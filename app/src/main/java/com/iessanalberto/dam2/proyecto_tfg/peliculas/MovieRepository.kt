package com.iessanalberto.dam2.proyecto_tfg.peliculas

import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.DiscoverMapper
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorDescubrir
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorPelicula
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Descubrir
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Discover
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula
import com.iessanalberto.dam2.proyecto_tfg.network.Network

class MovieRepository {
//    suspend fun fetchMoviePage(pageIndex: Int): GetMovieDiscoveryPage? {
//        val pagina = Network.clienteApi.getMovieDiscoveryPage(pageIndex)
//        if (!pagina.isSuccessful) {
//            return null
//        }
//
//        return pagina.body
//    }

    suspend fun getDescubrirPeliculas(): Descubrir? {
        val peticion = Network.clienteApi.getDescubrirPeliculas()
        if (!peticion.isSuccessful){
            return null
        }

        return MapeadorDescubrir.construirDe(
            respuesta = peticion.body
        )
    }

    suspend fun getPeliculaPorId(idPelicula: String): Pelicula? {
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