package com.example.marvelapp.ui.heroes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.marvelapp.R
import com.example.marvelapp.ui.heroes.fragment.HeroesFragment
import com.example.marvelapp.ui.heroes.fragment.HeroesDetailFragment


class HeroesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)

        val host = NavHostFragment.create(R.navigation.navhost)
        supportFragmentManager.beginTransaction().replace(R.id.container, host)
            .setPrimaryNavigationFragment(host).commit()
    }
}
