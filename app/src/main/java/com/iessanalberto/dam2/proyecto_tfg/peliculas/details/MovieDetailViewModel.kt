package com.iessanalberto.dam2.proyecto_tfg.peliculas.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Creditos
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula
import com.iessanalberto.dam2.proyecto_tfg.peliculas.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel: ViewModel() {
    private val repository = MovieRepository()

    private var _movieLiveData = MutableLiveData<Pelicula?>()
    private var _movieLiveDataCredits = MutableLiveData<Creditos?>()

    val peliculaLiveData: LiveData<Pelicula?> = _movieLiveData
    val movieLiveDataCredits: LiveData<Creditos?> = _movieLiveDataCredits

    fun tomarPelicula(idPelicula: String) {
        viewModelScope.launch {
            val pelicula = repository.getPeliculaPorId(idPelicula)
            _movieLiveData.postValue(pelicula)
        }
    }

    fun tomarCreditos(idPelicula: String) {
        viewModelScope.launch {
            val credits = repository.getCreditosPorIdPelicula(idPelicula)
            _movieLiveDataCredits.postValue(credits)
        }
    }
}