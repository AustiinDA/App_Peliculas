package com.iessanalberto.dam2.proyecto_tfg.peliculas.details

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.iessanalberto.dam2.proyecto_tfg.R
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelActorCarouselItemBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelCastTitleBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieCreditsBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieDetailsDataBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieDetailsHeaderBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieDetailsImageBinding
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Creditos
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Elenco
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.EquipoProduccion
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Pelicula
import com.iessanalberto.dam2.proyecto_tfg.epoxy.CargaModelos
import com.iessanalberto.dam2.proyecto_tfg.epoxy.ViewBindingKotlinModel
import com.iessanalberto.dam2.proyecto_tfg.network.respuestas.GetDevolverCreditosPorIdPelicula
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.squareup.picasso.Picasso

class MovieDetailEpoxyController(
//    private val enActorClicado: (String) -> Unit
) : EpoxyController() {

    //Comprobamos si la  respuesta es nula y asignamos propiedades a la view en los modelos

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var creditos: Creditos? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    var pelicula: Pelicula? = null
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

        if (pelicula == null) {
            return
        }

        //Asignamos los valores del constructor con los datos de la api, filtrandolos y manejandolos
        ImageEpoxyModel(

            backdropUrl = Constantes.POSTER_PATH + pelicula!!.fondo_url,
            posterUrl = Constantes.POSTER_PATH + pelicula!!.poster_url

        ).id("image").addTo(this)
        HeaderEpoxyModel(

            //Textos
            title = pelicula!!.titulo,
            genre = pelicula!!.generos.joinToString(
                separator = ", ",
                transform = { generos -> generos.nombre }),

            date = pelicula!!.fecha_lanzamiento,
            duration = pelicula!!.duracion,


            ).id("header").addTo(this)




        creditos?.equipoProduccion?.let {
            HeaderCreditsEpoxyModel(
                //iteramos y filtramos los directores
                director = it
                    .filter { item ->
                        item.trabajo == "Director"
                    }
            ).id("credits").addTo(this)
        }


//            actorImgUrl = Constantes.POSTER_PATH + credits!!.cast.joinToString(transform = { it.profile_path.toString() })


        DataEpoxyModel(
            resume = pelicula!!.resumen

        ).id("data").addTo(this)


        CastTextModel(
            texto = "Actores principales"
        ).id("texto").addTo(this)


// Display de actores con scroll horizontal
        val imgActor = Constantes.POSTER_PATH

        val cast = creditos?.elenco
            ?.map {
                CastCarouselItemEpoxyModel(it, img = imgActor + it.foto_url
//                    ,onClick = { actorId ->
//                        enActorClicado(actorId)
//                    }
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
        val director: List<EquipoProduccion>
    ) : ViewBindingKotlinModel<ModelMovieCreditsBinding>(R.layout.model_movie_credits) {
        override fun ModelMovieCreditsBinding.bind() {
            directorDescTextView.text = director.joinToString(
                transform = { item -> item.nombre }
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
        val elenco: Elenco,
        val img: String,
//        val onClick: (String) -> Unit
    ) : ViewBindingKotlinModel<ModelActorCarouselItemBinding>(R.layout.model_actor_carousel_item) {
        override fun ModelActorCarouselItemBinding.bind() {
            castTextView.text = elenco.nombre
            Picasso.get().load(img).placeholder(R.drawable.baseline_person_24).into(actorImg)
//            root.setOnClickListener {
//                onClick(elenco.id)
//            }
        }

    }
}