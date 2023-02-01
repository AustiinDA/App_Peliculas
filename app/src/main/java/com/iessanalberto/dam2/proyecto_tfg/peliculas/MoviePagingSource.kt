package com.iessanalberto.dam2.proyecto_tfg.peliculas

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iessanalberto.dam2.proyecto_tfg.dominio.mapeadores.DiscoverMapper
import com.iessanalberto.dam2.proyecto_tfg.network.Network
//Documentación rápida -> https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data?hl=es-419
class MoviePagingSource(
    private val repository: MovieRepository
) : PagingSource<Int, DiscoverInterfaceModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverInterfaceModel> {
        val pageNum = params.key ?: 1
        val prevKey = if (pageNum == 1) null else pageNum - 1
        val pagePetition = Network.clienteApi.getMovieDiscoveryPage(pageNum)

        pagePetition.exception?.let {
            return LoadResult.Error(it)
        }

        return LoadResult.Page(
            data = pagePetition.body.results.map { respuesta ->
                DiscoverInterfaceModel.Item(DiscoverMapper.buildOf(respuesta))
            },
            prevKey = prevKey,
            nextKey = pageNum + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, DiscoverInterfaceModel>): Int? {
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