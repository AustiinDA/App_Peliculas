package com.iessanalberto.dam2.proyecto_tfg

import android.graphics.Movie
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.card.MaterialCardView
import com.iessanalberto.dam2.proyecto_tfg.epoxy.MovieDetailEpoxyController
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyController = MovieDetailEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Observando el objeto con los cambios y una estructura para manejar posible errores
        viewModel.actualizarPelicula(78)
        viewModel.movieByIdLiveData.observe(this) { respuesta ->

            epoxyController.respuestaPeli = respuesta

            if (respuesta == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }

        viewModel.actualizarCreditosPelicula(78)
        viewModel.movieCreditsById.observe(this) { respuesta ->

            epoxyController.respuestaCreditos = respuesta

            if (respuesta == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }


        viewModel.actualizarPelicula(78)
        viewModel.actualizarCreditosPelicula(78)
        //Obtenemos el recyclerview del layout y llamamos a la funcion para establecer su función
        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}


