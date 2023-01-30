package com.iessanalberto.dam2.proyecto_tfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.iessanalberto.dam2.proyecto_tfg.epoxy.MovieDetailEpoxyController

class MovieDetailFragment : Fragment() {


    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyController = MovieDetailEpoxyController()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieByIdLiveData.observe(viewLifecycleOwner) {
//    TODO        movie -> epoxyController.
        }
    }


}