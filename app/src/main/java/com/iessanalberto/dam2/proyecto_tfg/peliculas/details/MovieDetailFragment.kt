package com.iessanalberto.dam2.proyecto_tfg.peliculas.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.iessanalberto.dam2.proyecto_tfg.R

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModels()
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

        val epoxyController = MovieDetailEpoxyController { personId ->
            val navDirections = MovieDetailFragmentDirections.actionMovieDetailFragmentToPersonDetailFragment()

            navDirections.personId = personId
            findNavController().navigate(navDirections)

        }

        viewModel.peliculaLiveData.observe(viewLifecycleOwner) { pelicula ->
            epoxyController.pelicula = pelicula

            if (pelicula == null) {
                Toast.makeText(
                    requireActivity(), "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigateUp()

                return@observe
            }
        }
//        viewModel.tomarPelicula(idPelicula = safeArgs.movieId)

//        viewModel.movieLiveDataCredits.observe(viewLifecycleOwner) { credits ->
//            epoxyController.credits = credits
//
//            if (credits == null) {
//                Toast.makeText(
//                    requireActivity(), "Llamada de red fallida",
//                    Toast.LENGTH_SHORT
//                ).show()
//                findNavController().navigateUp()
//
//                return@observe
//            }
//        }
//        viewModel.tomarCreditos(movieId = safeArgs.movieId)


        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)

    }


}