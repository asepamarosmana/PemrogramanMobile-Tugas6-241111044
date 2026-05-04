package com.example.jahitin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jahitin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)
        if (session.isLoggedIn()) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString()
            if (user.isNotEmpty()) {
                session.saveLoginSession(user)
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Isi nama dulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}