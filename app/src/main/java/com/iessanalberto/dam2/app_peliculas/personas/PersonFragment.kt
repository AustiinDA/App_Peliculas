package com.iessanalberto.dam2.app_peliculas.personas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iessanalberto.dam2.app_peliculas.R
import com.iessanalberto.dam2.app_peliculas.databinding.FragmentPersonDetailBinding


class PersonFragment: Fragment() {

    private val viewModel: PersonViewModel  by viewModels()

    private var _binding: FragmentPersonDetailBinding? = null
    private val binding get() = _binding!!

    private val epoxyController = PersonDetailEpoxyController()

    private val safeArgs: PersonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPersonDetailBinding.bind(view)

        viewModel.personLiveData.observe(viewLifecycleOwner) { person ->
            epoxyController.person = person

            if (person == null) {
                Toast.makeText(
                    requireActivity(), "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigateUp()

                return@observe
            }
        }

        viewModel.tomarPersona(personId = safeArgs.personId)

        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}