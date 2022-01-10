package com.example.uas19090075_mobileapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.example.uas19090075_mobileapp.R
import com.example.uas19090075_mobileapp.Util.SharedPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences //pilih yang utils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pref = SharedPreferences(this)

        // Login
        btn_login.setOnClickListener {
            pref.login = true
            val intent = Intent(this, MainActivity::class.java)
            val user = et_username.text.toString()
            val pass = et_password.text.toString()
            if ((user == "19090075" && pass == "19090075")){
                startActivity(intent)
                finish()
            }
        }

        // show and hidden password
        cb_showPass.setOnClickListener {
            if (cb_showPass.isChecked) {
                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }
}