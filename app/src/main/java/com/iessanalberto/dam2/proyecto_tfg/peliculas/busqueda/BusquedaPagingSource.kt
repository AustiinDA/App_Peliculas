package com.iessanalberto.dam2.proyecto_tfg.peliculas.busqueda

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorDescubrir
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Descubrir
import com.iessanalberto.dam2.proyecto_tfg.red.PeliculasNetwork

class BusquedaPagingSource(
    private val entradaBusqueda: String = "",
    private val exceptionCall: (InternalException) -> Unit
) : PagingSource<Int, Descubrir>() {

    sealed class InternalException(
        val excTitle: String,
        val excDescription: String = ""
    ) : Exception() {
        object BlankSearch : InternalException(
            excTitle = "Escribe un titulo para la busqueda"
        )

        object NoResults : InternalException(
            excTitle = "Vaya!",
            excDescription = "Parece que la entrada buscada no ha dado resultados"
        )
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Descubrir> {
        //Excepción de busqueda
        if (entradaBusqueda.isEmpty()) {
            val internalException = InternalException.BlankSearch
            exceptionCall(internalException)
            return LoadResult.Error(internalException) //algo pasa aqui
        }


        val numPagina = params.key ?: 1
        val llaveAnterior = if (numPagina == 1) null else numPagina - 1

        val peticionPagina = PeliculasNetwork.clienteApi.getBuscarPeliculaPorTitulo(
            pagina = numPagina,
            tituloPelicula = entradaBusqueda
        )

        if (peticionPagina.data?.code() == 404) {
            val internalException = InternalException.NoResults
            exceptionCall(internalException)
            return LoadResult.Error(internalException)
        }

//        if (pagePetition.bodyNullable?.results?.isEmpty() == true) {
//            val internalException = InternalException.NoResults
//            exceptionCall(internalException)
//            return LoadResult.Error(internalException)
//        }

        val data = peticionPagina.bodyNullable?.resultados?.filter { it.poster_url !=null }

        peticionPagina.exception?.let {
            return LoadResult.Error(it)
        }

        return LoadResult.Page(
            data = data?.map { respuesta ->
                MapeadorDescubrir.construirDe(respuesta)
            } ?: emptyList(),
            prevKey = llaveAnterior,
            nextKey = numPagina + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Descubrir>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}