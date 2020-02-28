package com.example.marvelapp.ui.heroes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapp.R
import com.example.marvelapp.ui.heroes.fragment.HeroesFragment
import com.example.marvelapp.ui.heroes.fragment.HeroesDetailFragment


class HeroesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.heroes_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HeroesFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is HeroesDetailFragment) {
                f.onBackPressed()
                return
            }
            if (f != null && f is HeroesFragment) {
                super.onBackPressed()
            }
        }
    }
}
