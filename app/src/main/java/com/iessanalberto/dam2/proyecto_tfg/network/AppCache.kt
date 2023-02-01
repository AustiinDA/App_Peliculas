package com.iessanalberto.dam2.proyecto_tfg.network

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Movie

//Datos en cache para reutilizar datos si nuestra conexion es mala y de esta manera no hacer llamadas de red innecesarias
//Ahorramos datos, bateria y recursos
object AppCache {
      val movieMap = mutableMapOf<Int, Movie>()
}