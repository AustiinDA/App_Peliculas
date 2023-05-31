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

        val epoxyController = MovieDetailEpoxyController()
//        { actorId ->
//            val navDirections = MovieDetailFragmentDirections.actionMovieDetailFragmentToPersonDetailFragment()
//
//            navDirections.actorId = actorId
//            findNavController().navigate(navDirections)
//
//        }

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
        viewModel.tomarPelicula(idPelicula = safeArgs.idPelicula)

        viewModel.movieLiveDataCredits.observe(viewLifecycleOwner) { creditos ->
            epoxyController.creditos = creditos

            if (creditos == null) {
                Toast.makeText(
                    requireActivity(), "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigateUp()

                return@observe
            }
            epoxyController.creditos = creditos
            epoxyController.requestModelBuild()

        }
        viewModel.tomarCreditos(idPelicula = safeArgs.idPelicula)


        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)

    }


}