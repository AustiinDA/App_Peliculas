package com.iessanalberto.dam2.proyecto_tfg.peliculas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes

// Usamos el flujo que ofrece el paginado para cargar muchos datos
// Lo sacamos directamente de la documentación android https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data?hl=es-419
class MoviesViewModel : ViewModel() {

    private val repository = MovieRepository()

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(
            pageSize = Constantes.PAGE_SIZE,
            prefetchDistance = Constantes.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        MoviesDataSource(repository)
    }.flow.cachedIn(viewModelScope)

}