package com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Elenco
import com.iessanalberto.dam2.proyecto_tfg.red.respuestas.GetDevolverCreditosPorIdPelicula

object MapeadorElenco {
    fun construirDe(
        elenco: GetDevolverCreditosPorIdPelicula.Elenco
    ): Elenco {
        return Elenco(
            id = elenco.id,
            genero = elenco.genero,
            nombre = elenco.nombre,
            foto_url = elenco.foto_url
        )
    }
}