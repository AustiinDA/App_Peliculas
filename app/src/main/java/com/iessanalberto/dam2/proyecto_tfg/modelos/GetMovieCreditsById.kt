package com.iessanalberto.dam2.proyecto_tfg.modelos

data class GetMovieCreditsById(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int = 0
)