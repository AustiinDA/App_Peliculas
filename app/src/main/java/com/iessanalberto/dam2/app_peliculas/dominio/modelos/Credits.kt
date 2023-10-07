package com.iessanalberto.dam2.app_peliculas.dominio.modelos

import com.iessanalberto.dam2.app_peliculas.network.respuestas.GetMovieCreditsById

data class Credits(
    val crew: List<GetMovieCreditsById.Crew> = listOf(),
    val cast: List<GetMovieCreditsById.Cast> = listOf(),
    val id: Int = 0
)