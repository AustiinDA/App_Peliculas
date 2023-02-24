package com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Search
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieSearchByQuery

object SearchMapper {
    fun buildOf(
        respuesta: GetMovieSearchByQuery
    ): Search {
        return Search(
            adult = respuesta.adult,
            backdrop_path = respuesta.backdrop_path,
            genre_ids = respuesta.genre_ids,
            id = respuesta.id,
            original_language = respuesta.original_language,
            original_title = respuesta.original_title,
            overview = respuesta.overview,
            popularity = respuesta.popularity,
            poster_path = respuesta.poster_path,
            release_date = respuesta.release_date,
            title = respuesta.title,
            video = respuesta.video,
            vote_average = respuesta.vote_average,
            vote_count = respuesta.vote_count
        )
    }
}
