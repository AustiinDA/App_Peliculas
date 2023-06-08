package com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.EquipoProduccion
import com.iessanalberto.dam2.proyecto_tfg.red.respuestas.GetDevolverCreditosPorIdPelicula

object MapeadorProduccion {
    fun construirDe(
        equipoProduccion: GetDevolverCreditosPorIdPelicula.EquipoProduccion
    ): EquipoProduccion {
        return EquipoProduccion(
            id = equipoProduccion.id,
            genero = equipoProduccion.genero,
            nombre = equipoProduccion.nombre,
            foto_url = equipoProduccion.foto_url,
            trabajo = equipoProduccion.trabajo
        )
    }
}