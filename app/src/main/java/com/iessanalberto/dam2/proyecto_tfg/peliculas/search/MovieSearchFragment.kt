package com.iessanalberto.dam2.proyecto_tfg.peliculas.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iessanalberto.dam2.proyecto_tfg.databinding.FragmentMovieSearchBinding

class MovieSearchFragment : Fragment() {
    private var _binding: FragmentMovieSearchBinding? = null
    private val binding get() = _binding

    private val viewModel: MovieSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieSearchBinding.bind  (view)
    }

    //TODO Desarrollar busqueda
}