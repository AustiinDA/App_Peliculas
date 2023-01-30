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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Observando el objeto con los cambios y una estructura para manejar posible errores
        viewModel.actualizarPelicula(11104)
        viewModel.movieByIdLiveData.observe(this) { movie ->

            epoxyController.movie = movie

            if (movie == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }

        viewModel.actualizarCreditosPelicula(11104)
        viewModel.movieCreditsById.observe(this) { credits ->

            epoxyController.credits = credits

            if (credits == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }


        viewModel.actualizarCreditosPelicula(11104)
        //Obtenemos el recyclerview del layout y llamamos a la funcion para establecer su función
        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}


