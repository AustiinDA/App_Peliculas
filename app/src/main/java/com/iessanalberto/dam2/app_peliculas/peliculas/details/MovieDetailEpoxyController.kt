package com.iessanalberto.dam2.app_peliculas.peliculas.details

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.iessanalberto.dam2.app_peliculas.R
import com.iessanalberto.dam2.app_peliculas.databinding.ModelActorCarouselItemBinding
import com.iessanalberto.dam2.app_peliculas.databinding.ModelCastTitleBinding
import com.iessanalberto.dam2.app_peliculas.databinding.ModelMovieCreditsBinding
import com.iessanalberto.dam2.app_peliculas.databinding.ModelMovieDetailsDataBinding
import com.iessanalberto.dam2.app_peliculas.databinding.ModelMovieDetailsHeaderBinding
import com.iessanalberto.dam2.app_peliculas.databinding.ModelMovieDetailsImageBinding
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Credits
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Movie
import com.iessanalberto.dam2.app_peliculas.epoxy.CargaModelos
import com.iessanalberto.dam2.app_peliculas.epoxy.ViewBindingKotlinModel
import com.iessanalberto.dam2.app_peliculas.network.respuestas.GetMovieCreditsById
import com.iessanalberto.dam2.app_peliculas.recursos.Constantes
import com.squareup.picasso.Picasso

class MovieDetailEpoxyController(
    private val onActorClicked: (Int) -> Unit
) : EpoxyController() {

    //Comprobamos si la  respuesta es nula y asignamos propiedades a la view en los modelos

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var credits: Credits? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    var movie: Movie? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    //Comprobamos si al construir los modelos obtenemos respuesta
    override fun buildModels() {
        if (isLoading) {
            CargaModelos().id("load").addTo(this)
            return
        }

        if (movie == null) {
            return
        }

        //Asignamos los valores del constructor con los datos de la api, filtrandolos y manejandolos
        ImageEpoxyModel(

            backdropUrl = Constantes.POSTER_PATH + movie!!.backdrop_path,
            posterUrl = Constantes.POSTER_PATH + movie!!.poster_path

        ).id("image").addTo(this)
        HeaderEpoxyModel(

            //Textos
            title = movie!!.title,
            genre = movie!!.genres.joinToString(
                separator = ", ",
                transform = { genre -> genre.name }),

            date = movie!!.release_date,
            duration = movie!!.runtime,


            ).id("header").addTo(this)




        credits?.crew?.let {
            HeaderCreditsEpoxyModel(
                //iteramos y filtramos los directores
                director = it
                    .filter { item ->
                        item.job == "Director"
                    }
            ).id("credits").addTo(this)
        }


//            actorImgUrl = Constantes.POSTER_PATH + credits!!.cast.joinToString(transform = { it.profile_path.toString() })


        DataEpoxyModel(
            resume = movie!!.overview

        ).id("data").addTo(this)


        CastTextModel(
            texto = "Actores principales"
        ).id("texto").addTo(this)


// Display de actores con scroll horizontal
        val imgActor = Constantes.POSTER_PATH

        val cast = credits?.cast
            ?.map {
                CastCarouselItemEpoxyModel(it, img = imgActor + it.profile_path,
                    onClick = { actorId ->
                        onActorClicked(actorId)
                    }
                ).id(it.id)
            }

        if (cast != null) {
            CarouselModel_()
                .id("carousel")
                .models(cast.take(8))
                .numViewsToShowOnScreen(3.5f)
                .addTo(this)
        }

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
        val director: List<GetMovieCreditsById.Crew>
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

    data class CastTextModel(
        val texto: String
    ) : ViewBindingKotlinModel<ModelCastTitleBinding>(R.layout.model_cast_title) {
        override fun ModelCastTitleBinding.bind() {
            textoActores.text = texto
        }
    }

    data class DataEpoxyModel(
        val resume: String
    ) : ViewBindingKotlinModel<ModelMovieDetailsDataBinding>(R.layout.model_movie_details_data) {
        override fun ModelMovieDetailsDataBinding.bind() {
            resumeTextView.text = resume
        }
    }

    data class CastCarouselItemEpoxyModel(
        val cast: GetMovieCreditsById.Cast,
        val img: String,
        val onClick: (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelActorCarouselItemBinding>(R.layout.model_actor_carousel_item) {
        override fun ModelActorCarouselItemBinding.bind() {
            castTextView.text = cast.original_name
            Picasso.get().load(img).placeholder(R.drawable.baseline_person_24).into(actorImg)
            root.setOnClickListener {
                onClick(cast.id)
            }
        }

    }
}