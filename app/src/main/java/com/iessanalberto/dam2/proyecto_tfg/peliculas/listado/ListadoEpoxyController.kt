package com.iessanalberto.dam2.proyecto_tfg.peliculas.listado

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.iessanalberto.dam2.proyecto_tfg.R
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelDiscoverListTitleBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieListItemBinding
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Descubrir
import com.iessanalberto.dam2.proyecto_tfg.epoxy.ViewBindingKotlinModel
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.squareup.picasso.Picasso


/*La clase MovieListEpoxyController se encarga de construir modelos de Epoxy en función
 *de los elementos de la lista paginada DiscoverInterfaceModel. Estos modelos pueden
 *representar elementos de cuadrícula y encabezados para la interfaz de descubrimiento de películas.
 *Además, se proporciona un onPeliculaSeleccionada callback para manejar las interacciones de selección de películas
*/

class ListadoEpoxyController(
    private val onPeliculaSeleccionada: (String) -> Unit
) : PagingDataEpoxyController<ListadoInterfaceModel>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: ListadoInterfaceModel?
    ): EpoxyModel<*> {
        return when (item!!) {
            is ListadoInterfaceModel.Item -> {
                val descubrir = (item as ListadoInterfaceModel.Item).descubrir
                MovieDiscoverGridItemEpoxyModel(
                    resultados = descubrir,
                    onClick = { idPelicula ->
                        onPeliculaSeleccionada(idPelicula)
                    }
                ).id(descubrir.id)
            }

            is ListadoInterfaceModel.Header -> {
                val header = (item as ListadoInterfaceModel.Header).text
                MovieDiscoverTitleEpoxyModel(header).id(header)
            }
        }

    }

    /*Estos dos modelos Epoxy construyen la interfaz de descubrimiento de películas.
     El primero representa un elemento con una imagen de póster y un título, mientras que el segundo representa
     un encabezado. Cada uno tiene una función bind() que se encarga de establecer los valores de las vistas en sus respectivos archivos de interfaz*/

    data class MovieDiscoverGridItemEpoxyModel(
        val onClick: (String) -> Unit,
        val resultados: Descubrir
    ) : ViewBindingKotlinModel<ModelMovieListItemBinding>(R.layout.model_movie_list_item) {
        override fun ModelMovieListItemBinding.bind() {
            val imgPoster = Constantes.POSTER_PATH
            Picasso.get().load(imgPoster + resultados.poster_url).into(movieImageView2)
            movieTitleTextView.text = resultados.titulo

            root.setOnClickListener { onClick(resultados.id) }
        }
    }

    data class MovieDiscoverTitleEpoxyModel(
        val title: String
    ) : ViewBindingKotlinModel<ModelDiscoverListTitleBinding>(R.layout.model_discover_list_title) {
        override fun ModelDiscoverListTitleBinding.bind() {
            headerText.text = title
        }

        override fun getSpanSize(totalSpan: Int, position: Int, items: Int): Int {
            return totalSpan
        }
    }

}