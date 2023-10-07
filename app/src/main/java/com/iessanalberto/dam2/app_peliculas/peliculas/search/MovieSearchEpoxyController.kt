package com.iessanalberto.dam2.app_peliculas.peliculas.search


import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.iessanalberto.dam2.app_peliculas.R
import com.iessanalberto.dam2.app_peliculas.databinding.ModelLocalErrorStateBinding
import com.iessanalberto.dam2.app_peliculas.databinding.ModelMovieListItemBinding
import com.iessanalberto.dam2.app_peliculas.dominio.modelos.Search
import com.iessanalberto.dam2.app_peliculas.epoxy.CargaModelos
import com.iessanalberto.dam2.app_peliculas.epoxy.ViewBindingKotlinModel
import com.iessanalberto.dam2.app_peliculas.recursos.Constantes
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class MovieSearchEpoxyController(private val onMovieSelected: (Int) -> Unit) :
    PagingDataEpoxyController<Search>() {

    var internalException: MovieSearchPagingSource.InternalException? = null
        set(value) {
            field = value
            if (internalException != null) {
                requestForcedModelBuild()
            }
        }


    override fun buildItemModel(currentPosition: Int, item: Search?): EpoxyModel<*> {
        val search = (item as Search)
        return MovieSearchGridItemEpoxyModel(
            searchResults = search,
            onClick = { movieId ->
                onMovieSelected(movieId)
            }
        ).id(item.id)
    }


    override fun addModels(models: List<EpoxyModel<*>>) {
        internalException?.let {
            ExceptionErrorStateEpoxyModel(internalException!!).id("error").addTo(this)
            return
        }

        if (models.isEmpty()) {
            CargaModelos().id("cargando").addTo(this)
            return
        }


        super.addModels(models)
    }

    data class MovieSearchGridItemEpoxyModel(
        val searchResults: Search,
        val onClick: (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelMovieListItemBinding>(R.layout.model_movie_list_item) {
        override fun ModelMovieListItemBinding.bind() {
            val imgPoster = Constantes.POSTER_PATH

            Picasso.get().load(imgPoster + searchResults.poster_path).into(movieImageView2)
            movieTitleTextView.text = searchResults.title

            root.setOnClickListener { onClick(searchResults.id) }
        }
    }

    data class ExceptionErrorStateEpoxyModel(
        val internalException: MovieSearchPagingSource.InternalException
    ) : ViewBindingKotlinModel<ModelLocalErrorStateBinding>(R.layout.model_local_error_state) {
        override fun ModelLocalErrorStateBinding.bind() {
            textViewErrorTitle.text = internalException.excTitle
            textViewErrorDescription.text = internalException.excDescription
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }
}
