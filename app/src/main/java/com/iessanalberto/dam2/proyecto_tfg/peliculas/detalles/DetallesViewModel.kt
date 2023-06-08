package com.iessanalberto.dam2.proyecto_tfg.peliculas.detalles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Creditos
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula
import com.iessanalberto.dam2.proyecto_tfg.peliculas.PeliculaRepository
import com.iessanalberto.dam2.proyecto_tfg.red.AppCache
import kotlinx.coroutines.launch
class DetallesViewModel: ViewModel() {
    private val repository = PeliculaRepository()

    private var _peliculaLiveData = MutableLiveData<Pelicula?>()
    private var _peliculaLiveDataCreditos = MutableLiveData<Creditos?>()

    val peliculaLiveData: LiveData<Pelicula?> = _peliculaLiveData
    val peliculaLiveDataCreditos: LiveData<Creditos?> = _peliculaLiveDataCreditos

    fun tomarPelicula(idPelicula: String) {
        //Observa los datos en cache de una pelicula
        val peliculaCacheada = AppCache.peliculaMap[idPelicula]
        if (peliculaCacheada != null) {
            _peliculaLiveData.postValue(peliculaCacheada)
            return
        }
        viewModelScope.launch {
            val pelicula = repository.getPeliculaPorId(idPelicula)
            _peliculaLiveData.postValue(pelicula)
            //Dentro de la corrutina, obtenemos respuesta del repositorio y le damos los datos a nuestro LiveData
            //Si la respuesta no es nula, actualizamos los datos en cache con esos datos no nulos
            pelicula?.let {
                AppCache.peliculaMap[idPelicula] = it
            }
        }
    }

    fun tomarCreditos(idPelicula: String) {
        //Observa los datos en cache de una pelicula
        val creditosCacheados = AppCache.creditosMap[idPelicula]
        if (creditosCacheados != null) {
            _peliculaLiveDataCreditos.postValue(creditosCacheados)
            return
        }
        viewModelScope.launch {
            val credits = repository.getCreditosPorIdPelicula(idPelicula)
            _peliculaLiveDataCreditos.postValue(credits)
        }
    }
}