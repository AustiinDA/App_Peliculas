package com.iessanalberto.dam2.proyecto_tfg.personas

import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.PersonMapper
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Person

import com.iessanalberto.dam2.proyecto_tfg.network.Network

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