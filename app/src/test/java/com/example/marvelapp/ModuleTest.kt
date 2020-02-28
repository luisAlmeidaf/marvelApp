package com.example.marvelapp

import com.example.marvelapp.domain.di.koin.modules.networkModule
import com.example.marvelapp.domain.di.koin.modules.uiModule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleTest: KoinTest {

    @Test
    fun `Should test if Koin modules are initializing correctly`() {
        startKoin {
            modules(listOf(uiModule, networkModule))
        }.checkModules()

        stopKoin()
    }
}