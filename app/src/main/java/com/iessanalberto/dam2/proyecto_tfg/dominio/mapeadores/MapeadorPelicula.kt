package com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetPeliculaPorId

object MapeadorPelicula {
    fun construirDe(
        respuesta: GetPeliculaPorId
    ): Pelicula {
        return Pelicula(
            fondo_url = respuesta.fondo_url,
            generos = respuesta.generos.map { MapeadorGenero.construirDe(it) },
            id = respuesta.id,
            resumen = respuesta.resumen,
            poster_url = respuesta.poster_url,
            fecha_lanzamiento = respuesta.fecha_lanzamiento,
            duracion = respuesta.duracion,
            titulo = respuesta.titulo
        )
    }
}