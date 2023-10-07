package com.iessanalberto.dam2.app_peliculas.dominio.mapeadores

import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Discover
import com.iessanalberto.dam2.app_peliculas.network.respuestas.GetMovieDiscoveryById

object DiscoverMapper {
    fun buildOf(
        respuesta: GetMovieDiscoveryById
    ): Discover {
        return Discover(
            adult = respuesta.adult,
            backdrop_path = respuesta.backdrop_path,
            genre_ids = respuesta.genre_ids,
            id = respuesta.id,
            overview = respuesta.overview,
            popularity = respuesta.popularity,
            poster_path = respuesta.poster_path,
            release_date = respuesta.release_date,
            title = respuesta.title,
            video = respuesta.video,
            vote_average = respuesta.vote_average
        )
    }
}