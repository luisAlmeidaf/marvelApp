package com.example.marvelapp.ui.heroes.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapp.data.datasource.OnGetMarvelCallback
import com.example.marvelapp.domain.model.Data
import com.example.marvelapp.data.repository.MarvelRepository
import com.example.marvelapp.data.repository.MarvelRepositoryImpl

class HeroesViewModel (private val repository: MarvelRepository = MarvelRepositoryImpl())
    : ViewModel() {

    private val heroesList: MutableLiveData<Data> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun getHeroesList() = heroesList

    fun errorMessage() = error

    fun getHeroes(offset: Int) {
        repository.getCharacter(offset, object : OnGetMarvelCallback {

            override fun onSuccess(response: Data) {
                heroesList.value = response
            }

            override fun onError() {
                error.postValue("An error has ocurred, please try again later.")
            }
        })
    }
}
