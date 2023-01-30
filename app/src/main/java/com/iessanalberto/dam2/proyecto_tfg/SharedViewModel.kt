package com.iessanalberto.dam2.proyecto_tfg

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Credits
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Movie
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetMovieCreditsById
import kotlinx.coroutines.launch

class SharedViewModel:ViewModel() {
    private val repositorio = SharedRepository()

    // Usamos LiveData y MutableLiveData para evitar que cualquier item que este escuchando a estos datos
    // pueda cambiar los datos, queremos que solo pueda leerlos. Y solo podremos modificarlo en esta SharedViewModel
    // siguiendo el modelo MVVM

    private val _movieByIdLiveData = MutableLiveData<Movie?>()
    private val _movieCreditsByIdLiveData = MutableLiveData<Credits?>()

    val movieByIdLiveData: LiveData<Movie?> = _movieByIdLiveData
    val movieCreditsById: LiveData<Credits?> = _movieCreditsByIdLiveData

    fun actualizarPelicula(movieId: Int){
        viewModelScope.launch {
            val respuesta = repositorio.getMovieById(movieId)

            _movieByIdLiveData.postValue(respuesta)
        }
    }

    fun actualizarCreditosPelicula(movieId: Int){
        viewModelScope.launch {
            val respuesta = repositorio.getMovieCreditsById(movieId)

            _movieCreditsByIdLiveData.postValue(respuesta)
        }
    }
}
