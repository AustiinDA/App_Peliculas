package com.iessanalberto.dam2.proyecto_tfg.network.respuestas

data class GetMovieCreditsById(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int = 0
) {
    data class Cast(
        val adult: Boolean = false,
        val gender: Int = 0,
        val id: Int = 0,
        val known_for_department: String = "",
        val name: String = "",
        val original_name: String = "",
        val popularity: Double = 0.0,
        val profile_path: String? = null,
        val cast_id: Int = 0,
        val character: String = "",
        val credit_id: String = "",
        val order: Int = 0
    )
    data class Crew(
        val adult: Boolean = false,
        val credit_id: String = "",
        val department: String = "",
        val gender: Int = 0,
        val id: Int = 0,
        val job: String = "",
        val known_for_department: String = "",
        val name: String = "",
        val original_name: String = "",
        val popularity: Double = 0.0,
        val profile_path: String? = null
    )

}