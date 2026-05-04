package com.example.jahitin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jahitin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var repo: TailorRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repo = TailorRepository(this)

        binding.btnRegister.setOnClickListener {
            val username = binding.etRegUser.text.toString().trim()
            val password = binding.etRegPass.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val result = repo.registerUser(username, password)
                if (result != -1L) {
                    Toast.makeText(this, "Registrasi berhasil! Silakan login.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Username sudah terdaftar!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }
}