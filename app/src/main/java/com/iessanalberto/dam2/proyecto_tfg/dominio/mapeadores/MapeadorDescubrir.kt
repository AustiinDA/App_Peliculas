package com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Descubrir
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetDevolverTodasPeliculas

object MapeadorDescubrir {
    fun construirDe(
      respuesta: GetDevolverTodasPeliculas
    ): Descubrir {
        return Descubrir(
            duracion = respuesta.duracion,
            fecha_lanzamiento = respuesta.fecha_lanzamiento,
            fondo_url = respuesta.fondo_url,
            id = respuesta.id,
            poster_url = respuesta.poster_url,
            resumen = respuesta.resumen,
            titulo = respuesta.titulo
            )
    }
}