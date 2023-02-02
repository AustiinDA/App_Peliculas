package com.iessanalberto.dam2.proyecto_tfg.peliculas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Credits
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Movie
import kotlinx.coroutines.launch

class MovieDetailViewModel: ViewModel() {
    private val repository = MovieRepository()

    private var _movieLiveData = MutableLiveData<Movie?>()
    private var _movieLiveDataCredits = MutableLiveData<Credits?>()

    val movieLiveData: LiveData<Movie?> = _movieLiveData
    val movieLiveDataCredits: LiveData<Credits?> = _movieLiveDataCredits

    fun tomarPelicula(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.getMovieById(movieId)
            _movieLiveData.postValue(movie)
        }
    }

    fun tomarCreditos(movieId: Int) {
        viewModelScope.launch {
            val credits = repository.getMovieCreditsById(movieId)
            _movieLiveDataCredits.postValue(credits)
        }
    }
}