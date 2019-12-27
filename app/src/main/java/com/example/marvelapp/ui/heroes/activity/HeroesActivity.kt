package com.example.marvelapp.ui.heroes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapp.R
import com.example.marvelapp.domain.di.koin.modules.uiModule
import com.example.marvelapp.ui.heroes.fragment.HeroesFragment
import org.koin.android.ext.android.startKoin

class HeroesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.heroes_activity)

        startKoin(this, listOf(uiModule))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HeroesFragment.newInstance())
                .commitNow()
        }
    }

}
