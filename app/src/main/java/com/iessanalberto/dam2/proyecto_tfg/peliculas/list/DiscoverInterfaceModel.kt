package com.iessanalberto.dam2.proyecto_tfg.peliculas.list

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Descubrir

sealed class DiscoverInterfaceModel {
    class Item(val descubrir: Descubrir): DiscoverInterfaceModel()
    class Header(val text: String): DiscoverInterfaceModel()
}