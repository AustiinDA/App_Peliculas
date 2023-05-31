package com.iessanalberto.dam2.proyecto_tfg.dominio.modelos

import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetDevolverCreditosPorIdPelicula

data class Creditos(
    var idPelicula: String,
    val elenco: List<Elenco> = listOf(),
    val equipoProduccion: List<EquipoProduccion> = listOf()
)
