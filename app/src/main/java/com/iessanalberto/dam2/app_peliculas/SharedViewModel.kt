package com.iessanalberto.dam2.app_peliculas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Credits
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Movie
import com.iessanalberto.dam2.app_peliculas.network.AppCache
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val repositorio = SharedRepository()

    // Usamos LiveData y MutableLiveData para evitar que cualquier item que este escuchando a estos datos
    // pueda cambiar los datos, queremos que solo pueda leerlos. Y solo podremos modificarlo en esta SharedViewModel
    // siguiendo el modelo MVVM

    private val _movieByIdLiveData = MutableLiveData<Movie?>()
    private val _movieCreditsByIdLiveData = MutableLiveData<Credits?>()

    val movieByIdLiveData: LiveData<Movie?> = _movieByIdLiveData
    val movieCreditsById: LiveData<Credits?> = _movieCreditsByIdLiveData

    fun actualizarPelicula(movieId: Int) {
        //Observa los datos en cache de una pelicula
        val cacheMovie = AppCache.movieMap[movieId]
        if (cacheMovie != null) {
            _movieByIdLiveData.postValue(cacheMovie)
            return
        }
        //En caso de fallo hara una llamada de la pelicula
        viewModelScope.launch {
            val respuesta = repositorio.getMovieById(movieId)

            _movieByIdLiveData.postValue(respuesta)

            //Dentro de la corrutina, obtenemos respuesta del repositorio y le damos los datos a nuestro LiveData
            //Si la respuesta no es nula, actualizamos los datos en cache con esos datos no nulos
            respuesta?.let {
                AppCache.movieMap[movieId] = it
            }
        }
    }

    fun actualizarCreditosPelicula(movieId: Int) {
        viewModelScope.launch {
            val respuesta = repositorio.getMovieCreditsById(movieId)

            _movieCreditsByIdLiveData.postValue(respuesta)
        }
    }
}
