package com.iessanalberto.dam2.proyecto_tfg.red

import retrofit2.Response

data class RespuestaSimple<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {
    //Podemos declarar objetos dentro de una clase con un companion object,
    // este construira los metodos para pasar la información necesaria que recibimos de la peticion red
    companion object {
        fun <T> success(data: Response<T>): RespuestaSimple<T> {
            return RespuestaSimple(
                status = Status.Success,
                data = data,
                exception = null
            )
        }


        fun <T> failure(exception: Exception): RespuestaSimple<T> {
            return RespuestaSimple(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }

//   Clase restrictiva con mas control de herencia
//   Se encargará de comprobar si una llamada a la red es exitosa o no
    sealed class Status {
        object Success : Status()
        object Failure : Status()
    }

    val failed: Boolean
        get() = this.status == Status.Failure

    val isSuccessful: Boolean
        get() = !failed && this.data?.isSuccessful == true

    //    El cuerpo recibe cualquier tipo parametrizado
    val body: T
        get() = this.data!!.body()!!

    val bodyNullable: T?
        get() = this.data?.body()
}
