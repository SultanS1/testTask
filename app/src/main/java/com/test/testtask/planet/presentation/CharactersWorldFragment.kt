package com.test.testtask.planet.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.testtask.R
import com.test.testtask.common.Constants
import com.test.testtask.databinding.FragmentCharactersWorldBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersWorldFragment : Fragment(R.layout.fragment_characters_world) {

    private val binding by viewBinding<FragmentCharactersWorldBinding>()
    private val viewModel by viewModel<CharactersWorldViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        triggerActions()
        observeState()
        binding.arrowBackBtn.setOnClickListener { findNavController().navigateUp() }
    }

    private fun triggerActions(){
        val id = arguments?.getInt(Constants.PLANET_ID)
        id?.let { viewModel.getPlanet(it) }
    }

    private fun observeState(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.planet.collectLatest {
                binding.planetNameNameTxt.text = it.name
                binding.diameterValueTxt.text = it.diameter
                binding.climatTypeTxt.text = it.climat
                binding.gravitationValueTxt.text = it.gravity
                binding.populationValueTxt.text = it.population
                binding.placeValueTxt.text = it.place
            }
        }
    }

}