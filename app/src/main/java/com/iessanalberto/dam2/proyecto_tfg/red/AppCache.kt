package com.iessanalberto.dam2.proyecto_tfg.red

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Creditos
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula

//Datos en cache para reutilizar datos si nuestra conexion es mala y de esta manera no hacer llamadas de red innecesarias
//Ahorramos datos, bateria y recursos
object AppCache {
      val peliculaMap = mutableMapOf<String, Pelicula>()
      val creditosMap = mutableMapOf<String, Creditos>()
}