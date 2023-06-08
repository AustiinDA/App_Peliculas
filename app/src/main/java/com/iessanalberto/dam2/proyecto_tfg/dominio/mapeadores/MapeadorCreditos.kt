package com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Creditos
import com.iessanalberto.dam2.proyecto_tfg.red.respuestas.GetDevolverCreditosPorIdPelicula

object MapeadorCreditos {
    fun construirDe(
        respuesta: GetDevolverCreditosPorIdPelicula
    ): Creditos {
        return Creditos(
            idPelicula = respuesta.idPelicula,
            elenco = respuesta.elenco.map { MapeadorElenco.construirDe(it) },
            equipoProduccion = respuesta.equipoProduccion.map { MapeadorProduccion.construirDe(it) }
        )
    }
}