package com.iessanalberto.dam2.proyecto_tfg.peliculas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.iessanalberto.dam2.proyecto_tfg.R
import com.iessanalberto.dam2.proyecto_tfg.SharedViewModel
import com.iessanalberto.dam2.proyecto_tfg.epoxy.MovieDetailEpoxyController

class MovieDetailFragment : Fragment() {


    private val viewModel: MovieDetailViewModel by viewModels()
//    by lazy {
//        ViewModelProvider(this)[SharedViewModel::class.java]
//    }

    private val epoxyController = MovieDetailEpoxyController()
    private val safeArgs: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieLiveData.observe(viewLifecycleOwner) { movie ->
            epoxyController.movie = movie

            if (movie == null) {
                Toast.makeText(
                    requireActivity(), "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigateUp()

                return@observe
            }
        }
        viewModel.tomarPelicula(movieId = safeArgs.movieId)

        viewModel.movieLiveDataCredits.observe(viewLifecycleOwner) { credits ->
            epoxyController.credits = credits

            if (credits == null) {
                Toast.makeText(
                    requireActivity(), "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigateUp()

                return@observe
            }
        }
        viewModel.tomarCreditos(movieId = safeArgs.movieId)


        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)

    }


}