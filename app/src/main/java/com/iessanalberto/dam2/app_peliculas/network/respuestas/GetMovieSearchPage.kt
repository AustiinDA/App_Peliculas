package com.iessanalberto.dam2.app_peliculas.network.respuestas

data class GetMovieSearchPage(
    val page: Int,
    val results: List<GetMovieSearchByQuery> = emptyList(),
    val total_pages: Int,
    val total_results: Int
)
