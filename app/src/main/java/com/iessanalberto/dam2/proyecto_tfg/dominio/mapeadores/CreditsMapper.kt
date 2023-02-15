package com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Credits
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieCreditsById

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