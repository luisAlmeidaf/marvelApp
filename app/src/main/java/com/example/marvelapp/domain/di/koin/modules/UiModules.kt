package com.example.marvelapp.domain.di.koin.modules

import com.example.marvelapp.data.repository.MarvelRepository
import com.example.marvelapp.data.repository.MarvelRepositoryImpl
import com.example.marvelapp.ui.heroes.adapter.HeroesAdapter
import com.example.marvelapp.ui.heroes.viewmodel.HeroesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    factory { HeroesAdapter(mutableListOf()) }

    viewModel { HeroesViewModel(get()) }

    single<MarvelRepository> { MarvelRepositoryImpl()}
}

