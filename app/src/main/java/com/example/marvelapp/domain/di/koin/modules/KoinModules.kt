package com.example.marvelapp.domain.di.koin.modules

import com.example.marvelapp.ui.heroes.adapter.HeroesAdapter
import org.koin.dsl.module.module

val uiModule = module {
    factory { HeroesAdapter(mutableListOf()) }
}