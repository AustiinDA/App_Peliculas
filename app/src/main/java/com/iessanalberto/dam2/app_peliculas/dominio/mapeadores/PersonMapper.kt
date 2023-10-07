package com.iessanalberto.dam2.app_peliculas.dominio.mapeadores

import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Person
import com.iessanalberto.dam2.app_peliculas.network.respuestas.GetPersonById

object PersonMapper {
    fun buildOf(
        respuesta: GetPersonById
    ): Person {
        return Person(
            adult = respuesta.adult,
            also_known_as = respuesta.also_known_as,
            biography = respuesta.biography,
            birthday = respuesta.birthday,
            deathday = respuesta.deathday,
            gender = respuesta.gender,
            homepage = respuesta.homepage,
            id = respuesta.id,
            imdb_id = respuesta.imdb_id,
            known_for_department = respuesta.known_for_department,
            name = respuesta.name,
            place_of_birth = respuesta.place_of_birth,
            popularity = respuesta.popularity,
            profile_path = respuesta.profile_path
        )
    }
}