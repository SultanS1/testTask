package com.test.testtask.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testtask.characters.data.entity.toUi
import com.test.testtask.characters.data.repository.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: CharactersRepository
): ViewModel() {

    private val _characters = MutableStateFlow<List<CharacterUiState>>(listOf())
    val characters: StateFlow<List<CharacterUiState>> get() = _characters

    fun getCharacters(id: Int){
        viewModelScope.launch{
            val result = repository.getCharacters(id)
            _characters.value = result.map { it.toUi() }
        }
    }

}