package com.example.jahitin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jahitin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var session: SessionManager
    private lateinit var repo: TailorRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)
        repo = TailorRepository(this)

        if (session.isLoggedIn()) {
            moveToDashboard()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUser.text.toString().trim()
            val password = binding.etPass.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (repo.checkUser(username, password)) {
                    session.saveLoginSession(username)
                    Toast.makeText(this, "Selamat datang, $username!", Toast.LENGTH_SHORT).show()
                    moveToDashboard()
                } else {
                    Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Isi semua bidang!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun moveToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}