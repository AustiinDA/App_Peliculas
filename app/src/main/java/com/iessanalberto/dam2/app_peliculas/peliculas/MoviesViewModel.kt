package com.iessanalberto.dam2.app_peliculas.peliculas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iessanalberto.dam2.app_peliculas.recursos.Constantes

// Usamos el flujo que ofrece el paginado para cargar muchos datos
// Lo sacamos directamente de la documentación android https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data?hl=es-419
class MoviesViewModel : ViewModel() {

    private val repository = MovieRepository()

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(
            pageSize = Constantes.PAGE_SIZE,
            initialLoadSize = 1,
            prefetchDistance = Constantes.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        MoviePagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    //Añadido de headers, aun no se muestra correctamente
/*        .map {
        it.insertSeparators { model: DiscoverInterfaceModel?, model2: DiscoverInterfaceModel? ->

            //Separador del header
            if (model == null) {
                return@insertSeparators DiscoverInterfaceModel.Header("Prueba")
            }

            if (model2 == null) {
                return@insertSeparators null
            }

            //Items
            if (model is DiscoverInterfaceModel.Header || model2 is DiscoverInterfaceModel.Header) {
                return@insertSeparators null
            }

            val movie1 = (model as DiscoverInterfaceModel.Item).discover
            val movie2 = (model2 as DiscoverInterfaceModel.Item).discover
            return@insertSeparators if (movie2.genre_ids != movie1.genre_ids) {
                DiscoverInterfaceModel.Header("Genero: ${movie2.genre_ids}")
            } else {
                null
            }
        }
    }*/

}