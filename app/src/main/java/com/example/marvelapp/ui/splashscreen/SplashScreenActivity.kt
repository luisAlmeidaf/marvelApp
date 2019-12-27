package com.example.marvelapp.ui.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapp.R
import android.content.Intent
import android.os.Handler
import com.example.marvelapp.ui.heroes.activity.HeroesActivity
import androidx.core.os.HandlerCompat.postDelayed





class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val handle = Handler()
        handle.postDelayed(Runnable { showHeroes() }, 2000)
    }

    private fun showHeroes() {
        val intent = Intent(this@SplashScreenActivity, HeroesActivity::class.java)
        startActivity(intent)
        finish()
    }
}
