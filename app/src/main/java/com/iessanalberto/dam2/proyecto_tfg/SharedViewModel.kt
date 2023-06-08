package com.iessanalberto.dam2.proyecto_tfg

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Creditos
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula
import com.iessanalberto.dam2.proyecto_tfg.red.AppCache
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val repositorio = SharedRepository()

    // Usamos LiveData y MutableLiveData para evitar que cualquier item que este escuchando a estos datos
    // pueda cambiar los datos, queremos que solo pueda leerlos. Y solo podremos modificarlo en esta SharedViewModel
    // siguiendo el modelo MVVM

    private val _movieByIdLiveData = MutableLiveData<Pelicula?>()
    private val _movieCreditsByIdLiveData = MutableLiveData<Creditos?>()

    val movieByIdLiveData: LiveData<Pelicula?> = _movieByIdLiveData
    val movieCreditsById: LiveData<Creditos?> = _movieCreditsByIdLiveData

    fun actualizarPelicula(idPelicula: String) {
        //Observa los datos en cache de una pelicula
        val cacheMovie = AppCache.peliculaMap[idPelicula]
        if (cacheMovie != null) {
            _movieByIdLiveData.postValue(cacheMovie)
            return
        }
        //En caso de fallo hara una llamada de la pelicula
        viewModelScope.launch {
            val respuesta = repositorio.getPeliculasPorId(idPelicula)

            _movieByIdLiveData.postValue(respuesta)

            //Dentro de la corrutina, obtenemos respuesta del repositorio y le damos los datos a nuestro LiveData
            //Si la respuesta no es nula, actualizamos los datos en cache con esos datos no nulos
            respuesta?.let {
                AppCache.peliculaMap[idPelicula] = it
            }
        }
    }

//    fun actualizarCreditosPelicula(movieId: Int) {
//        viewModelScope.launch {
//            val respuesta = repositorio.getMovieCreditsById(movieId)
//
//            _movieCreditsByIdLiveData.postValue(respuesta)
//        }
//    }
}
