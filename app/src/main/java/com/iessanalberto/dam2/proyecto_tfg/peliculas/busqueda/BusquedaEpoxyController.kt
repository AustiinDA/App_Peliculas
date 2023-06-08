package com.iessanalberto.dam2.proyecto_tfg.peliculas.busqueda


import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.iessanalberto.dam2.proyecto_tfg.R
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelLocalErrorStateBinding
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelMovieListItemBinding
import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Descubrir
import com.iessanalberto.dam2.proyecto_tfg.epoxy.CargaModelos
import com.iessanalberto.dam2.proyecto_tfg.epoxy.ViewBindingKotlinModel
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class BusquedaEpoxyController(private val onMovieSelected: (String) -> Unit) :
    PagingDataEpoxyController<Descubrir>() {

    var internalException: BusquedaPagingSource.InternalException? = null
        set(value) {
            field = value
            if (internalException != null) {
                requestForcedModelBuild()
            }
        }


    override fun buildItemModel(currentPosition: Int, item: Descubrir?): EpoxyModel<*> {
        val busqueda = (item as Descubrir)
        return MovieSearchGridItemEpoxyModel(
            resultadosBusqueda = busqueda,
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
        val resultadosBusqueda: Descubrir,
        val onClick: (String) -> Unit
    ) : ViewBindingKotlinModel<ModelMovieListItemBinding>(R.layout.model_movie_list_item) {
        override fun ModelMovieListItemBinding.bind() {
            val imgPoster = Constantes.POSTER_PATH

            Picasso.get().load(imgPoster + resultadosBusqueda.poster_url).into(movieImageView2)
            movieTitleTextView.text = resultadosBusqueda.titulo

            root.setOnClickListener { onClick(resultadosBusqueda.id) }
        }
    }

    data class ExceptionErrorStateEpoxyModel(
        val internalException: BusquedaPagingSource.InternalException
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
