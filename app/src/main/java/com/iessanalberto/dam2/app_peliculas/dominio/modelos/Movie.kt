package com.iessanalberto.dam2.app_peliculas.dominio.modelos


data class Movie(
    val backdrop_path: String? = "",
    val genres: List<Genre> = listOf(),
    val id: Int = 0,
    val overview: String = "",
    val poster_path: String? = "",
    val release_date: String = "",
    val runtime: Int = 0,
    val title: String = "",
)
