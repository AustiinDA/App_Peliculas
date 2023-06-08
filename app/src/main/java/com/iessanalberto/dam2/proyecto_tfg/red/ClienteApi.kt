package com.iessanalberto.dam2.proyecto_tfg.red

import com.iessanalberto.dam2.proyecto_tfg.interfaces.ServicioMiApiPeliculas
import com.iessanalberto.dam2.proyecto_tfg.red.respuestas.*
import retrofit2.Response

class ClienteApi(
    private val servicioMiApiPeliculas: ServicioMiApiPeliculas
) {

    suspend fun getDescubrirPeliculas(): RespuestaSimple<GetDevolverPeliculas> {
        return llamadaApiSegura {
            servicioMiApiPeliculas.getDescubrirPeliculas()
        }
    }

    suspend fun getDescubrirPeliculasPaginadas(indicePagina: Int): RespuestaSimple<GetDevolverPeliculasPaginadas> {
        return llamadaApiSegura {
            servicioMiApiPeliculas.getDescubrirPeliculasPaginadas(
                indicePagina
            )
        }
    }

    suspend fun getPeliculaPorId(idPelicula: String): RespuestaSimple<GetPeliculaPorId> {
        return llamadaApiSegura {
            servicioMiApiPeliculas.getPeliculaPorId(
                idPelicula
            )
        }
    }

    suspend fun getCreditosPorIdPelicula(idPelicula: String): RespuestaSimple<GetDevolverCreditosPorIdPelicula> {
        return llamadaApiSegura {
            servicioMiApiPeliculas.getCreditosPorPeliculaId(
                idPelicula
            )
        }
    }


    suspend fun getBuscarPeliculaPorTitulo(
        pagina: Int,
        tituloPelicula: String
    ): RespuestaSimple<GetDevolverPeliculasPaginadas> {
        return llamadaApiSegura {
            servicioMiApiPeliculas.getBusquedaPeliculasPaginadas(
                pagina,
                tituloPelicula
            )
        }
    }

    // Funcion parametrizada que recibe un indicador de excepciones y devuelve una respuesta parametrizada
    private inline fun <T> llamadaApiSegura(llamadaApi: () -> Response<T>): RespuestaSimple<T> {
        return try {
            RespuestaSimple.success(llamadaApi.invoke())
        } catch (e: Exception) {
            RespuestaSimple.failure(e)
        }
    }
}