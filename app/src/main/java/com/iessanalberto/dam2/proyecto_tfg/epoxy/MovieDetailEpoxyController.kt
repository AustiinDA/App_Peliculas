package com.iessanalberto.dam2.proyecto_tfg.epoxy

import com.airbnb.epoxy.EpoxyController
import com.iessanalberto.dam2.proyecto_tfg.R
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieCreditsBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieDetailsDataBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieDetailsHeaderBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieDetailsImageBinding
import com.iessanalberto.dam2.proyecto_tfg.modelos.Crew
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieById
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieCreditsById
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.squareup.picasso.Picasso

class MovieDetailEpoxyController : EpoxyController() {

    //Comprobamos si la  respuesta es nula y asignamos propiedades a la view en los modelos

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var respuestaCreditos: GetMovieCreditsById? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    var respuestaPeli: GetMovieById? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    //Comprobamos si al construir los modelos obtenermos respuesta
    override fun buildModels() {
        if (isLoading) {
            CargaModelos().id("load").addTo(this)
            return
        }

        if (respuestaPeli == null) {
            //TODO estado de fallo
            return
        }

        //Asignamos los valores del constructor con los datos de la api, filtrandolos y manejandolos
        ImageEpoxyModel(

            backdropUrl = Constantes.POSTER_PATH + respuestaPeli!!.backdrop_path,
            posterUrl = Constantes.POSTER_PATH + respuestaPeli!!.poster_path

        ).id("image").addTo(this)
        HeaderEpoxyModel(

            //Textos
            title = respuestaPeli!!.title,
            genre = respuestaPeli!!.genres.joinToString(
                separator = ", ",
                transform = { genre -> genre.name }),

            date = respuestaPeli!!.release_date,
            duration = respuestaPeli!!.runtime,


            ).id("header").addTo(this)




        respuestaCreditos?.crew?.let {
            HeaderCreditsEpoxyModel(
                //iteramos y filtramos los directores
                director = it
                    .filter { item ->
                        item.job == "Director"
                    }
            ).id("credits").addTo(this)
        }


        DataEpoxyModel(
            resume = respuestaPeli!!.overview

        ).id("data").addTo(this)
    }

    data class HeaderEpoxyModel(
        val title: String,
        val genre: String,
        val date: String,
        val duration: Int
    ) : ViewBindingKotlinModel<ModelMovieDetailsHeaderBinding>(R.layout.model_movie_details_header) {
        override fun ModelMovieDetailsHeaderBinding.bind() {


            tituloTextView.text = title
            movieImageView.setImageResource(R.drawable.ic_baseline_movie_24)
            genreDescTextView.text = genre
            calendarImageView.setImageResource(R.drawable.ic_baseline_today_24)
            yearTextView.text = date
            duracionTextView.text = duration.toString()
            duracionImageView.setImageResource(R.drawable.ic_baseline_access_time_24)


        }
    }

    data class HeaderCreditsEpoxyModel(
        val director: List<Crew>
    ) : ViewBindingKotlinModel<ModelMovieCreditsBinding>(R.layout.model_movie_credits) {
        override fun ModelMovieCreditsBinding.bind() {
            directorDescTextView.text = director.joinToString(
                transform = { item -> item.name }
            )
        }
    }

    data class ImageEpoxyModel(
        val backdropUrl: String,
        val posterUrl: String

    ) : ViewBindingKotlinModel<ModelMovieDetailsImageBinding>(R.layout.model_movie_details_image) {
        override fun ModelMovieDetailsImageBinding.bind() {

            Picasso.get().load(backdropUrl).into(headerImg)
            Picasso.get().load(posterUrl).into(posterImg)
        }
    }

    data class DataEpoxyModel(
        val resume: String
    ) : ViewBindingKotlinModel<ModelMovieDetailsDataBinding>(R.layout.model_movie_details_data) {
        override fun ModelMovieDetailsDataBinding.bind() {
            resumeTextView.text = resume
        }
    }
}