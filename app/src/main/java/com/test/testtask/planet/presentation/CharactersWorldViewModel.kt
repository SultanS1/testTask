package com.test.testtask.planet.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testtask.planet.data.entity.toUi
import com.test.testtask.planet.data.repository.CharacterWorldRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersWorldViewModel(
    private val repository: CharacterWorldRepository
): ViewModel() {

    private val _planet = MutableStateFlow<PlanetUiState>(PlanetUiState("", "", "", "", "", ""))
    val planet: StateFlow<PlanetUiState>get() = _planet

    fun getPlanet(id: Int){
        viewModelScope.launch{
            val result = repository.getPlanet(id)
            if (result != null) {
                _planet.value = result.toUi()
            }
        }
    }


}