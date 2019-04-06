package com.example.univerapp.view.auth

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.univerapp.MainActivity
import com.example.univerapp.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tvRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
