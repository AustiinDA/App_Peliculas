package com.iessanalberto.dam2.proyecto_tfg.peliculas.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.iessanalberto.dam2.proyecto_tfg.R
import com.iessanalberto.dam2.proyecto_tfg.databinding.FragmentMovieListBinding
import com.iessanalberto.dam2.proyecto_tfg.peliculas.MoviesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieListBinding.bind(view)

        val epoxyController = MovieListEpoxyController { movieSelectedId ->

            val direccion =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment()
            direccion.idPelicula = movieSelectedId

            findNavController().navigate(direccion)
        }

        //Informacion de la PagingSource para presentarla con flow
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData: PagingData<DiscoverInterfaceModel> ->
                epoxyController.submitData(pagingData)
            }
        }

        binding.epoxyRecyclerView.setController(epoxyController)
    }


    private fun onMovieSelected(idPelicula: String) {
        val direccion = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment()
        direccion.idPelicula = idPelicula

        findNavController().navigate(direccion)
    }
}

