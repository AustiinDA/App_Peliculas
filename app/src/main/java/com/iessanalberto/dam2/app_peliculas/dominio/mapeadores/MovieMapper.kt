package com.iessanalberto.dam2.app_peliculas.dominio.mapeadores

import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Movie
import com.iessanalberto.dam2.app_peliculas.network.respuestas.GetMovieById

object MovieMapper {
    fun buildOf(
        respuesta: GetMovieById
    ): Movie {
        return Movie(
            backdrop_path = respuesta.backdrop_path,
            id = respuesta.id,
            overview = respuesta.overview,
            poster_path = respuesta.poster_path,
            release_date = respuesta.release_date,
            runtime = respuesta.runtime,
            title = respuesta.title,
            genres = respuesta.genres.map { GenreMapper.buildOf(it) }
        )
    }
}