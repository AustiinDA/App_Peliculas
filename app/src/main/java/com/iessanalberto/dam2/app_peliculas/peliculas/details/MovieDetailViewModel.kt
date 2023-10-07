package com.iessanalberto.dam2.app_peliculas.peliculas.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Credits
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Movie
import com.iessanalberto.dam2.app_peliculas.peliculas.MovieRepository
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