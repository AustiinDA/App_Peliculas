package com.iessanalberto.dam2.proyecto_tfg.dominio.modelos

import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieById


data class Movie(

    val backdrop_path: String = "",
    val genres: List<GetMovieById.Genre> = listOf(),
    val id: Int = 0,
    val overview: String = "",
    val poster_path: String = "",
    val release_date: String = "",
    val runtime: Int = 0,
    val title: String = "",
)