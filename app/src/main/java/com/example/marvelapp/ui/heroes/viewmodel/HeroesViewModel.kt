package com.example.marvelapp.ui.heroes.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.model.Data
import com.example.marvelapp.data.repository.MarvelRepository
import com.example.marvelapp.data.repository.MarvelRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroesViewModel (private val repository: MarvelRepository = MarvelRepositoryImpl())
    : ViewModel() {

    private val heroesList: MutableLiveData<Data> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun getHeroesList() = heroesList

    fun errorMessage() = error

    fun getHeroes(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
        val callData = repository.getCharacter(offset)
            withContext(Dispatchers.Main){
                heroesList.value = callData
            }
    }
    }
}
