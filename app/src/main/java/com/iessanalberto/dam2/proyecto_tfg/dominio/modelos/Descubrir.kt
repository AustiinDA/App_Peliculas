package com.iessanalberto.dam2.proyecto_tfg.dominio.modelos

data class Descubrir(
    val duracion: Int = 0,
    val fecha_lanzamiento: String = "",
    val fondo_url: String? = "",
//    val generos: List<Genero> = listOf(),
    val id: String = "",
    val poster_url: String? = "",
    val resumen: String = "",
    val titulo: String = ""
)
