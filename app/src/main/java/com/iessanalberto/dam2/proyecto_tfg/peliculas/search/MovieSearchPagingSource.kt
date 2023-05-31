package com.iessanalberto.dam2.proyecto_tfg.peliculas.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorDescubrir
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.MapeadorPelicula
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.SearchMapper
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Descubrir
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Search
import com.iessanalberto.dam2.proyecto_tfg.network.Network

class MovieSearchPagingSource(
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


        val pageNum = params.key ?: 1
        val prevKey = if (pageNum == 1) null else pageNum - 1

        val pagePetition = Network.clienteApi.getBuscarPeliculaPorTitulo(
            pagina = pageNum,
            tituloPelicula = entradaBusqueda
        )

        if (pagePetition.data?.code() == 404) {
            val internalException = InternalException.NoResults
            exceptionCall(internalException)
            return LoadResult.Error(internalException)
        }

//        if (pagePetition.bodyNullable?.results?.isEmpty() == true) {
//            val internalException = InternalException.NoResults
//            exceptionCall(internalException)
//            return LoadResult.Error(internalException)
//        }

        val data = pagePetition.bodyNullable?.resultados?.filter { it.poster_url !=null }

        pagePetition.exception?.let {
            return LoadResult.Error(it)
        }

        return LoadResult.Page(
            data = data?.map { respuesta ->
                MapeadorDescubrir.construirDe(respuesta)
            } ?: emptyList(),
            prevKey = prevKey,
            nextKey = pageNum + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Descubrir>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}