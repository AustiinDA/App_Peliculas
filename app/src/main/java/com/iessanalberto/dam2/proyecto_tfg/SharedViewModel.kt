package com.iessanalberto.dam2.proyecto_tfg

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieCreditsById
import kotlinx.coroutines.launch

class SharedViewModel:ViewModel() {
    private val repositorio = SharedRepository()

    // Usamos LiveData y MutableLiveData para evitar que cualquier item que este escuchando a estos datos
    // pueda cambiar los datos, queremos que solo pueda leerlos. Y solo podremos modificarlo en esta SharedViewModel
    // siguiendo el modelo MVVM

    private val _movieByIdLiveData = MutableLiveData<GetMovieById>()
//    private val _movieCreditsByIdLiveData = MutableLiveData<GetMovieCreditsById>()


    val movieByIdLiveData: LiveData<GetMovieById?> = _movieByIdLiveData
//    val movieCreditsByIdLiveData: LiveData<GetMovieCreditsById?> = _movieCreditsByIdLiveData

    fun actualizarPelicula(movieId: Int){
        viewModelScope.launch {
            val respuesta = repositorio.getMovieById(564)

            _movieByIdLiveData.postValue(respuesta)
        }
    }

//    fun actualizarCreditosPelicula(movieId: Int){
//        viewModelScope.launch {
//            val respuesta = repositorio.getMovieCreditsById(564)
//
//            _movieCreditsByIdLiveData.postValue(respuesta)
//        }
//    }

}
