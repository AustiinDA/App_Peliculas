package com.iessanalberto.dam2.app_peliculas.dominio.mapeadores

import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Genre
import com.iessanalberto.dam2.app_peliculas.network.respuestas.GetMovieById

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