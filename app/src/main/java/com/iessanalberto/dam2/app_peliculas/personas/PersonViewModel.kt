package com.iessanalberto.dam2.app_peliculas.personas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Person
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {

    private val repository = PersonRepository()
    private var _personLiveData = MutableLiveData<Person?>()

    val personLiveData: LiveData<Person?> = _personLiveData

    fun tomarPersona(personId: Int){
        viewModelScope.launch {
            val person = repository.getPersonById(personId)
            _personLiveData.postValue(person)
        }
    }




}