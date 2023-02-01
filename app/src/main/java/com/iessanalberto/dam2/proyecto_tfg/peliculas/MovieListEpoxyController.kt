package com.iessanalberto.dam2.proyecto_tfg.peliculas

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.iessanalberto.dam2.proyecto_tfg.R
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelDiscoverListTitleBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieListItemBinding
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Discover
import com.iessanalberto.dam2.proyecto_tfg.epoxy.ViewBindingKotlinModel
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ObsoleteCoroutinesApi
import java.text.FieldPosition


@OptIn(ObsoleteCoroutinesApi::class)
class MovieListEpoxyController(
    private val onMovieSelected: (Int) -> Unit
) : PagingDataEpoxyController<DiscoverInterfaceModel>() {

    override fun buildItemModel(currentPosition: Int, item: DiscoverInterfaceModel?): EpoxyModel<*> {
        return when (item!!){
            is DiscoverInterfaceModel.Item -> {
                val discover = (item as DiscoverInterfaceModel.Item).discover
                MovieDiscoverGridItemEpoxyModel(
                    discoverResults = discover,
                    onClick = {movieId ->
                        onMovieSelected(movieId)
                    }
                ).id(discover.id)
            }

            is DiscoverInterfaceModel.Header -> {
                val header = (item as DiscoverInterfaceModel.Header).text
                MovieDiscoverTitleEpoxyModel(header).id(header)
            }
        }

    }

    data class MovieDiscoverGridItemEpoxyModel(
        val onClick: (Int) -> Unit,
        val discoverResults: Discover
    ) : ViewBindingKotlinModel<ModelMovieListItemBinding>(R.layout.model_movie_list_item) {
        override fun ModelMovieListItemBinding.bind() {
            val imgPoster = Constantes.POSTER_PATH

            Picasso.get().load(imgPoster + discoverResults.poster_path).into(movieImageView2)
            movieTitleTextView.text = discoverResults.title

            root.setOnClickListener{onClick(discoverResults.id)}
        }
    }

    data class MovieDiscoverTitleEpoxyModel(
        val title: String
    ): ViewBindingKotlinModel<ModelDiscoverListTitleBinding>(R.layout.model_discover_list_title) {
        override fun ModelDiscoverListTitleBinding.bind() {
            headerText.text = title
        }
        override fun getSpanSize(totalSpan: Int, position: Int, items: Int): Int{
            return totalSpan
        }
    }

}