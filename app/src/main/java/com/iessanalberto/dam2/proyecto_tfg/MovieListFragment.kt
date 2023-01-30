package com.iessanalberto.dam2.proyecto_tfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.iessanalberto.dam2.proyecto_tfg.databinding.FragmentMovieListBinding
import com.iessanalberto.dam2.proyecto_tfg.peliculas.MovieListPagingEpoxyController
import com.iessanalberto.dam2.proyecto_tfg.peliculas.MoviesViewModel

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieListBinding.bind(view)

        val epoxyController = MovieListPagingEpoxyController { movieSelectedId ->

//            val navDirection: NavGraphDirections.action

        }
    }

    private fun onMovieSelected(movieId: Int) {

    }
}

