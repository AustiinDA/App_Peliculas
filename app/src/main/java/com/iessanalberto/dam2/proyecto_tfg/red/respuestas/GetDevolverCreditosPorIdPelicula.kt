package com.iessanalberto.dam2.proyecto_tfg.red.respuestas

data class GetDevolverCreditosPorIdPelicula(
    val elenco: List<Elenco>,
    val equipoProduccion: List<EquipoProduccion>,
    val idPelicula: String
) {
    data class Elenco(
        val foto_url: String?,
        val genero: Int,
        val id: String,
        val nombre: String
    )

    data class EquipoProduccion(
        val foto_url: String?,
        val genero: Int,
        val id: String,
        val nombre: String,
        val trabajo: String
    )
}