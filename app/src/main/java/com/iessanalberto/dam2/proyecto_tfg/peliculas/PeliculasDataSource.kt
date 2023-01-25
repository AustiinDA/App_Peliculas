package com.iessanalberto.dam2.proyecto_tfg.peliculas

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iessanalberto.dam2.proyecto_tfg.network.Network
import com.iessanalberto.dam2.proyecto_tfg.respuestas.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.respuestas.GetMovieDiscovery

class PeliculasDataSource(
    private val repository: MovieRepository

) : PagingSource<Int, GetMovieDiscovery>() {
    override fun getRefreshKey(state: PagingState<Int, GetMovieDiscovery>): Int? {
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

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetMovieDiscovery> {
        val pageNum = params.key ?: 1
        val prevKey = if (pageNum == 1) null else pageNum - 1

        val pagePetition  = Network.clienteApi.getMovieDiscoveryPage(pageNum)

        pagePetition.exception?.let {
            return LoadResult.Error(it)
        }
        return LoadResult.Page(
            data = emptyList(), //todo incroporar el mapeado de las peliculas
            prevKey = prevKey,
            nextKey = pagePetition.body.page

        )
    }

//    private fun getPageIndexnFromNext(next: String?): Int? {
//        return next?.split("&page=")?.get(1)?.toInt()
//    }

}