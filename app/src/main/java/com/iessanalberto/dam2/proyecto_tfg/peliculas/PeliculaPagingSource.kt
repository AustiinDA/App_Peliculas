package com.iessanalberto.dam2.proyecto_tfg.peliculas

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorDescubrir
import com.iessanalberto.dam2.proyecto_tfg.red.PeliculasNetwork
import com.iessanalberto.dam2.proyecto_tfg.peliculas.listado.ListadoInterfaceModel

//Documentación rápida -> https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data?hl=es-419
class PeliculaPagingSource(
    private val repository: PeliculaRepository
) : PagingSource<Int, ListadoInterfaceModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListadoInterfaceModel> {
        try {
            val numPagina = params.key ?: 1
            val pagAnterior = if (numPagina == 1) null else numPagina - 1
            val peticionPagina = PeliculasNetwork.clienteApi.getDescubrirPeliculasPaginadas(numPagina)

            peticionPagina.exception?.let {
                return LoadResult.Error(it)
            }

            val resultados = peticionPagina.body.resultados
            val pagSiguiente = if (resultados.isNotEmpty()) numPagina + 1 else null

            return LoadResult.Page(
                data = peticionPagina.body.resultados.map { respuesta ->
                    ListadoInterfaceModel.Item(MapeadorDescubrir.construirDe(respuesta))
                },
                prevKey = pagAnterior,
                nextKey = pagSiguiente
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ListadoInterfaceModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}