package com.example.uas19090075_mobileapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.uas19090075_mobileapp.R
import com.example.uas19090075_mobileapp.Util.SharedPreferences

class SplashScreenActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        pref = SharedPreferences(this)

        // intent mengarah ke menu login
        Handler(Looper.getMainLooper()).postDelayed({
            var inten = Intent()
            if(!pref.login){
                startActivity(
                    Intent(this@SplashScreenActivity,
                        LoginActivity::class.java)
                )
            } else{
                inten = Intent(this, MainActivity::class.java)
            }
            startActivity(inten)
            finish()
        }, 3000)
    }
}