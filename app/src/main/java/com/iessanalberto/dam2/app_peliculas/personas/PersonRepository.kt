package com.iessanalberto.dam2.app_peliculas.personas

import com.iessanalberto.dam2.app_peliculas.dominio.mapeadores.PersonMapper
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Person

import com.iessanalberto.dam2.app_peliculas.network.Network

class PersonRepository {

    suspend fun getPersonById(personId: Int): Person? {
        val peticion = Network.clienteApi.getPersonById(personId)

        if (!peticion.isSuccessful) {
            return null
        }

        return PersonMapper.buildOf(
            respuesta = peticion.body
        )

    }
}