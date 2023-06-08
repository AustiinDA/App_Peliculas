package com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Genero
import com.iessanalberto.dam2.proyecto_tfg.red.respuestas.GetPeliculaPorId

object MapeadorGenero {
    fun construirDe(
        genero: GetPeliculaPorId.Genero
    ): Genero {
        return Genero(
            nombre = genero.nombre
        )
    }

}