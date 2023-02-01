package com.iessanalberto.dam2.proyecto_tfg.dominio.modelos

data class Discover(
    val adult: Boolean,
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val id: Int = 0,
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean,
    val vote_average: Double = 0.0
)