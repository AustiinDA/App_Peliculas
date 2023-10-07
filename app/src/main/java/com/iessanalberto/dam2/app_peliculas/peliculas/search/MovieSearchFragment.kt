package com.iessanalberto.dam2.app_peliculas.peliculas.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iessanalberto.dam2.app_peliculas.R
import com.iessanalberto.dam2.app_peliculas.databinding.FragmentMovieSearchBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieSearchFragment : Fragment(R.layout.fragment_movie_search) {
    private var _binding: FragmentMovieSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieSearchViewModel by viewModels()

    private var text = ""
    private val handler = Handler(Looper.getMainLooper())
    private val search = Runnable {
        viewModel.buscarPeticion(text)
    }

    @ObsoleteCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieSearchBinding.bind(view)

        val epoxyController = MovieSearchEpoxyController { movieSelectedId ->
            val direccion =
                MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment()
            direccion.movieId = movieSelectedId

            findNavController().navigate(direccion)
        }

        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
        //Busca a traves del search runnable y reinicia los callbacks
        //volviendo a buscar con cierto delay
        binding.searchInputText.doAfterTextChanged {
            text = it?.toString() ?: ""
            handler.removeCallbacks(search)
            handler.postDelayed(search, 650L)
        }

        lifecycleScope.launch {
            viewModel.flow.collectLatest { data ->
                epoxyController.internalException = null
                epoxyController.submitData(data)
            }
        }

        viewModel.internalExceptionEventLiveData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { exception ->
                epoxyController.internalException = exception
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}