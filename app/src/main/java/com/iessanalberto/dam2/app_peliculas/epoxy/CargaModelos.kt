package com.iessanalberto.dam2.app_peliculas.epoxy

import com.iessanalberto.dam2.app_peliculas.R
import com.iessanalberto.dam2.app_peliculas.databinding.ModelLoadBinding

class CargaModelos: ViewBindingKotlinModel<ModelLoadBinding>(R.layout.model_load) {
    override fun ModelLoadBinding.bind() {}

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}