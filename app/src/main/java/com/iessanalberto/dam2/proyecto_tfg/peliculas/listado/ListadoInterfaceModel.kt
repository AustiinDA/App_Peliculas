package com.iessanalberto.dam2.proyecto_tfg.peliculas.listado

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Descubrir
/*Esta sealed class se utiliza para representar diferentes elementos de la interfaz de descubrimiento
* al definir estos subtipos dentro de la sealed class, se limita el conjunto de posibles tipos que pueden
* ser utilizados como instancias de DiscoverInterfaceModel. Esto permite un manejo más seguro y exhaustivo de los diferentes elementos de la interfaz.*/
sealed class ListadoInterfaceModel {
    class Item(val descubrir: Descubrir): ListadoInterfaceModel()
    class Header(val text: String): ListadoInterfaceModel()
}