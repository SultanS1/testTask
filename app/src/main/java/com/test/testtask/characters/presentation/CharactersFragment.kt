package com.test.testtask.characters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.testtask.R
import com.test.testtask.common.Constants
import com.test.testtask.databinding.FragmentCharactersBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val binding by viewBinding<FragmentCharactersBinding>()
    private val characterAdapter by lazy {
        CharacterRvAdapter()
    }
    private val viewModel by viewModel<CharactersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmName = arguments?.getString(Constants.FILM_NAME)
        binding.movieNameTxt.text = filmName
        setupAdapters()
        triggerActions()
        observeState()
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupAdapters(){
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = characterAdapter
        characterAdapter.clickToWorld = {
            val bundle = Bundle()
            bundle.putInt(Constants.PLANET_ID, it.id.substring(it.id.length-1, it.id.length-2).toInt())
            findNavController().navigate(R.id.action_charactersFragment_to_charactersWorldFragment, bundle)
        }
    }

    private fun triggerActions(){
        val id = arguments?.getInt(Constants.EPISODE_ID)
        id?.let { viewModel.getCharacters(it) }
    }

    private fun observeState(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.characters.collectLatest {
                characterAdapter.setList(it)
            }
        }
    }

}