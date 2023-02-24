package com.iessanalberto.dam2.proyecto_tfg.peliculas.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.iessanalberto.dam2.proyecto_tfg.recursos.Event

class MovieSearchViewModel : ViewModel() {
    private var inputSearch: String = ""
    private var movieSource: MovieSearchPagingSource? = null
        get() {
            if (field == null || field?.invalid == true) {
                field = MovieSearchPagingSource(inputSearch) { exception ->
                    _internalExceptionEventLiveData.postValue(Event(exception))
                }
            }
            return field
        }

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(
            pageSize = Constantes.PAGE_SIZE,
            prefetchDistance = Constantes.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        movieSource!!
    }.flow.cachedIn(viewModelScope)

    private val _internalExceptionEventLiveData = MutableLiveData<Event<MovieSearchPagingSource.InternalException>>()
    val internalExceptionEventLiveData: LiveData<Event<MovieSearchPagingSource.InternalException>> = _internalExceptionEventLiveData
    fun buscarPeticion(search: String) {
        inputSearch = search
        movieSource?.invalidate()
    }
}