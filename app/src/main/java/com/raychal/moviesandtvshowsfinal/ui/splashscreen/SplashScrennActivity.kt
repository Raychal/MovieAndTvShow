package com.raychal.moviesandtvshowsfinal.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.raychal.moviesandtvshowsfinal.R
import com.raychal.moviesandtvshowsfinal.ui.home.MainActivity

class SplashScrennActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screnn)

        val delay = 3000

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, delay.toLong())
    }
}