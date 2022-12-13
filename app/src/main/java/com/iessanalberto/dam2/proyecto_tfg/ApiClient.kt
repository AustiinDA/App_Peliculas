package com.iessanalberto.dam2.proyecto_tfg

import com.iessanalberto.dam2.proyecto_tfg.interfaces.TheMovieDBService
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieCreditsById
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import retrofit2.Response

class ApiClient(
    private val theMovieDBService: TheMovieDBService
) {
    suspend fun getMovieById(movieId: Int): Response<GetMovieById> {
        return theMovieDBService.getMovieById(movieId, Constantes.API_KEY, Constantes.LANGUAGE)
    }

    suspend fun getMovieCreditsById(movieId: Int): Response<GetMovieCreditsById> {
        return theMovieDBService.getMovieCreditsById(movieId, Constantes.API_KEY, Constantes.LANGUAGE)
    }

}