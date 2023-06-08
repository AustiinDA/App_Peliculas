package com.iessanalberto.dam2.proyecto_tfg.dominio.modelos

data class Creditos(
    var idPelicula: String,
    val elenco: List<Elenco> = listOf(),
    val equipoProduccion: List<EquipoProduccion> = listOf()
)
