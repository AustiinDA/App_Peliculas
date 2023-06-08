package com.iessanalberto.dam2.proyecto_tfg.peliculas

import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorCreditos
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorDescubrir
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorPelicula
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Creditos
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Descubrir
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula
import com.iessanalberto.dam2.proyecto_tfg.red.PeliculasNetwork

class PeliculaRepository {
/*La idea de los repositorios viene dada por la arquitectura que estamos usando, ya que necesitamos separar
* las responsabilidades de cada capa de nuestra app. Este repositorio hace uso de nuestra capa de red para
* responder cada petición que hace nuestra Api.
* Mas info: https://developer.android.com/jetpack/guide?hl=es-419
*/
    suspend fun getDescubrirPeliculas(): Descubrir? {
        val peticion = PeliculasNetwork.clienteApi.getDescubrirPeliculas()
        if (!peticion.isSuccessful){
            return null
        }

        return MapeadorDescubrir.construirDe(
            respuesta = peticion.body
        )
    }

    suspend fun getPeliculaPorId(idPelicula: String): Pelicula? {
        val peticion = PeliculasNetwork.clienteApi.getPeliculaPorId(idPelicula)

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

    suspend fun getCreditosPorIdPelicula(peliculaId: String): Creditos? {
        val peticion = PeliculasNetwork.clienteApi.getCreditosPorIdPelicula(peliculaId)

        if (peticion.failed) {
            return null
        }

        if (!peticion.isSuccessful) {
            return null
        }

        return MapeadorCreditos.construirDe(
            respuesta = peticion.body
        )
    }

}