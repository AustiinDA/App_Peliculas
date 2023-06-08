package com.iessanalberto.dam2.proyecto_tfg.red.respuestas

data class GetPeliculaPorId(
    val duracion: Int,
    val fecha_lanzamiento: String,
    val fondo_url: String,
    val generos: List<Genero>,
    val id: String,
    val poster_url: String,
    val resumen: String,
    val titulo: String
) {
    data class Genero(
        val nombre: String
    )
}