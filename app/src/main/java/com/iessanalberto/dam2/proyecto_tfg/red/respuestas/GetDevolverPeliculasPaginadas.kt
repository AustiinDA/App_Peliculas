package com.iessanalberto.dam2.proyecto_tfg.red.respuestas

data class GetDevolverPeliculasPaginadas(
    val numero_pagina: Int,
    val paginas_totales: Int,
    val resultados: List<GetDevolverPeliculas> = emptyList(),
    val resultados_totales: Int
)