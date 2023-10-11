package com.example.pocketinvestor.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pocketinvestor.R
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeComponents()
    }
        private fun initializeComponents(){
        val buttonSignUp: MaterialButton = findViewById(R.id.sign_up_button_for_start)
        val buttonLogIn: MaterialButton = findViewById(R.id.log_in_button_for_start)
            buttonSignUp.setOnClickListener {
                val intent = Intent(this@MainActivity, RegActivity::class.java)
                startActivity(intent)
            }
            buttonLogIn.setOnClickListener {
                val intent = Intent(this@MainActivity, AuthActivity::class.java)
                startActivity(intent)
            }
    }
}