package com.iessanalberto.dam2.proyecto_tfg.network.respuestas

data class GetDevolverTodasPeliculasPaginado(
    val numero_pagina: Int,
    val paginas_totales: Int,
    val resultados: List<GetDevolverTodasPeliculas> = emptyList(),
    val resultados_totales: Int
)