package com.iessanalberto.dam2.proyecto_tfg.network

import com.iessanalberto.dam2.proyecto_tfg.interfaces.TheMovieDBService
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.*
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import retrofit2.Response

class ApiClient(
    private val theMovieDBService: TheMovieDBService
) {
    suspend fun getMovieById(movieId: Int): SimpleResponse<GetMovieById> {
        return llamadaApiSegura {
            theMovieDBService.getMovieById(
                movieId,
                Constantes.API_KEY,
                Constantes.LANGUAGE
            )
        }
    }

    suspend fun getMovieCreditsById(movieId: Int): SimpleResponse<GetMovieCreditsById> {
        return llamadaApiSegura {
            theMovieDBService.getMovieCreditsById(
                movieId,
                Constantes.API_KEY,
                Constantes.LANGUAGE
            )
        }
    }

    suspend fun getPopularMoviesPage(pageIndex: Int): SimpleResponse<GetPopularMovies> {
        return llamadaApiSegura {
            theMovieDBService.getPopularMoviesPage(
                pageIndex,
                Constantes.API_KEY,
                Constantes.LANGUAGE
            )
        }
    }

    suspend fun getMovieSearchPage(pageIndex: Int, movieTitle: String): SimpleResponse<GetMovieSearchPage> {
        return llamadaApiSegura {
            theMovieDBService.getMovieSearchPage(
                pageIndex,
                Constantes.API_KEY,
                Constantes.LANGUAGE,
                movieTitle
                )
        }
    }

    suspend fun getMovieDiscoveryPage(pageIndex: Int): SimpleResponse<GetMovieDiscoveryPage> {
        return llamadaApiSegura {
            theMovieDBService.getMovieDiscoveryPage(
                pageIndex,
                Constantes.API_KEY,
                Constantes.LANGUAGE
            )
        }
    }
    suspend fun getMovieDiscoveryById(): SimpleResponse<GetMovieDiscoveryById>{
        return llamadaApiSegura {
            theMovieDBService.getMovieDiscoveryById(
                Constantes.API_KEY,
                Constantes.LANGUAGE
            )
        }
    }

    // Funcion parametrizada que recibe un indicador de excepciones y devuelve una respuesta parametrizada
    private inline fun <T> llamadaApiSegura(llamadaApi: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(llamadaApi.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
//
//    suspend fun getGenres(genreId: Int): SimpleResponse<GetGenres>{
//        return llamadaApiSegura { theMovieDBService.getGenres(
//            Constantes.API_KEY,
//            Constantes.LANGUAGE
//        ) }
//    }
}