package com.example.marvelapp

import android.app.Application
import com.example.marvelapp.domain.di.koin.modules.networkModule
import com.example.marvelapp.domain.di.koin.modules.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class heroesApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{

            androidLogger()

            androidContext(this@heroesApplication)

            androidFileProperties()

            modules(listOf(uiModule, networkModule))
        }
    }

}