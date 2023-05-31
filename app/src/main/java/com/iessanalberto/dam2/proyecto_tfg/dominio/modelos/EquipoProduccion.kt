package com.iessanalberto.dam2.proyecto_tfg.dominio.modelos

data class EquipoProduccion(
    val id: String,
    val genero: Int,
    val nombre: String,
    val foto_url: String?,
    val trabajo: String
)
