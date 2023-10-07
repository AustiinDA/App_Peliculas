package com.iessanalberto.dam2.app_peliculas.peliculas.list

import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Discover

sealed class DiscoverInterfaceModel {
    class Item(val discover: Discover): DiscoverInterfaceModel()
    class Header(val text: String): DiscoverInterfaceModel()
}