package com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Genre
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieById

object GenreMapper {
    fun buildOf(
        genre: GetMovieById.Genre
    ): Genre {
        return Genre(
            id = genre.id,
            name = genre.name
        )
    }
}