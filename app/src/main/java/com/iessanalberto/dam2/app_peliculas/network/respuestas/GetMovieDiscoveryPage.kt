package com.iessanalberto.dam2.app_peliculas.network.respuestas

data class GetMovieDiscoveryPage(
    val page: Int,
    val results: List<GetMovieDiscoveryById> = emptyList(),
    val total_pages: Int,
    val total_results: Int
)