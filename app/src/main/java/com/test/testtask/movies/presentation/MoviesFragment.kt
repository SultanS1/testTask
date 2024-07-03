package com.test.testtask.movies.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.testtask.R
import com.test.testtask.databinding.FragmentMoviesBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val binding by viewBinding<FragmentMoviesBinding>()
    private val moviesAdapter by lazy {
        MoviesRvAdapter()
    }
    private val viewModel by viewModel<MoviesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        triggerActions()
        observeState()
        setupSearchView()
    }

    private fun setupAdapters(){
        binding.moviesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun triggerActions(){
        viewModel.getMovies()
    }

    private fun observeState(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.movies.collectLatest {
                moviesAdapter.setList(it)
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    if (!newText.isNullOrEmpty()){
                        viewModel.movies.collectLatest {
                            val res = it.filter { it.filmName.contains(newText) }
                            if(res.isEmpty()){
                                binding.moviesRv.isVisible = false
                                binding.noItemsFoundTxt.isVisible = true
                            }else{
                                binding.moviesRv.isVisible = true
                                binding.noItemsFoundTxt.isVisible = false
                                moviesAdapter.setList(res)
                            }
                        }
                    }else{
                        observeState()
                    }
                }
                return true
            }
        })
    }

}