package com.iessanalberto.dam2.proyecto_tfg.dominio.modelos

data class Pelicula(
    val fondo_url: String? = "",
    val generos: List<Genero> = listOf(),
    var id: String = "",
    val resumen: String = "",
    val poster_url: String? = "",
    val fecha_lanzamiento: String = "",
    val duracion: Int = 0,
    val titulo: String = ""
)