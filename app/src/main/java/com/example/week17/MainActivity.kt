package com.example.week17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week17.databinding.ActivityMainBinding
import com.example.week17.webservice.Constant

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnLogout()
    }

    private fun btnLogout(){
        binding.btnLogout.setOnClickListener {
            Constant.clearToken(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}