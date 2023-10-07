package com.iessanalberto.dam2.app_peliculas.dominio.mapeadores

import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Credits
import com.iessanalberto.dam2.app_peliculas.network.respuestas.GetMovieCreditsById

object CreditsMapper {
    fun buildOf(
        respuesta: GetMovieCreditsById,
        crew: List<GetMovieCreditsById.Crew>,
        cast: List<GetMovieCreditsById.Cast>
    ): Credits {
        return Credits(
            crew = crew,
            cast = cast,
            id = respuesta.id
        )
    }
}