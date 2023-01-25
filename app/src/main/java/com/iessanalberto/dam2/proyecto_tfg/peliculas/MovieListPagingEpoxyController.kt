package com.iessanalberto.dam2.proyecto_tfg.peliculas

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.iessanalberto.dam2.proyecto_tfg.R
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieListItemBinding
import com.iessanalberto.dam2.proyecto_tfg.epoxy.ViewBindingKotlinModel
import com.iessanalberto.dam2.proyecto_tfg.respuestas.GetMovieDiscovery
import com.iessanalberto.dam2.proyecto_tfg.respuestas.GetPopularMovies
import com.squareup.picasso.Picasso
import retrofit2.Response

class MovieListPagingEpoxyController(
    private val onMovieSelected: (Int) -> Unit
) : PagedListEpoxyController<GetMovieDiscovery.Result>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetMovieDiscovery.Result?
    ): EpoxyModel<*> {

        return MovieGridItemEpoxyModel(
            item!!.id,
            item.poster_path,
            item.title,
            onMovieSelected = onMovieSelected
        )

//        return MovieGridItemEpoxyModel(item!!.results.joinToString(
//            transform = { poster -> poster.poster_path }
//        ), item.results.joinToString(
//            transform = { titulo -> titulo.title }
//        )).id(item.results.(
//            transform = { id -> id.id }

    }

    data class MovieGridItemEpoxyModel(
        val movieId: Int,
        val poster: String,
        val titulo: String,
        val onMovieSelected: (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelMovieListItemBinding>(R.layout.model_movie_list_item) {

        override fun ModelMovieListItemBinding.bind() {
            Picasso.get().load(poster).into(movieImageView2)
            movieTitleTextView.text = titulo
        }
    }

}