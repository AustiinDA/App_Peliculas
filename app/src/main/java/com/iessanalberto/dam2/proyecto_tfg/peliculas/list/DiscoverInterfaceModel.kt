package com.iessanalberto.dam2.proyecto_tfg.peliculas.list

import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Discover

sealed class DiscoverInterfaceModel {
    class Item(val discover: Discover): DiscoverInterfaceModel()
    class Header(val text: String): DiscoverInterfaceModel()
}