package com.example.marvelapp

import com.example.marvelapp.data.repository.MarvelRepository
import com.example.marvelapp.domain.di.koin.modules.networkModule
import com.example.marvelapp.domain.di.koin.modules.uiModule
import com.example.marvelapp.ui.heroes.adapter.HeroesAdapter
import com.example.marvelapp.ui.heroes.viewmodel.HeroesViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class UiTest: KoinTest {

    private val adapter: HeroesAdapter by inject()
    private val viewModel: HeroesViewModel by inject()
    private val repository: MarvelRepository by inject()


    @Before
    fun setup(){
        startKoin {
            modules(listOf(uiModule, networkModule))
        }
    }

    @After
    fun tearDown(){
        stopKoin()
    }

    @Test
    fun `should test if api was created`(){
        Assert.assertNotNull(adapter)
    }

    @Test
    fun `should test if viewModel was created`(){
        Assert.assertNotNull(viewModel)
    }

    @Test
    fun `should test if repository was created`(){
        Assert.assertNotNull(repository)
    }
}