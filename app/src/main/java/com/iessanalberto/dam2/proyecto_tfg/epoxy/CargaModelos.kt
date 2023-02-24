package com.iessanalberto.dam2.proyecto_tfg.epoxy

import com.iessanalberto.dam2.proyecto_tfg.R
import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelLoadBinding

class CargaModelos: ViewBindingKotlinModel<ModelLoadBinding>(R.layout.model_load) {
    override fun ModelLoadBinding.bind() {}

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}