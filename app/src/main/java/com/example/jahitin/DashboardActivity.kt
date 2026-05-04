package com.example.jahitin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jahitin.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var repo: TailorRepository
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repo = TailorRepository(this)
        session = SessionManager(this)

        // Tampilkan username dan buat bisa diklik untuk ke Profil
        val currentUsername = session.getUsername()
        binding.tvWelcome.text = "Halo, $currentUsername!"
        binding.tvWelcome.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.rvTailors.layoutManager = LinearLayoutManager(this)
        refreshData()

        binding.fabAdd.setOnClickListener {
            showAddDialog()
        }

        binding.btnLogout.setOnClickListener {
            performLogout()
        }
    }

    private fun refreshData() {
        val data = repo.getAllTailors()
        val adapter = TailorAdapter(data)
        binding.rvTailors.adapter = adapter
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_tailor, null)
        val etName = dialogView.findViewById<EditText>(R.id.etTailorName)
        val etSkill = dialogView.findViewById<EditText>(R.id.etTailorSkill)
        val etLoc = dialogView.findViewById<EditText>(R.id.etTailorLocation)
        val etPrice = dialogView.findViewById<EditText>(R.id.etTailorPrice)

        AlertDialog.Builder(this)
            .setTitle("Tambah Penjahit Baru")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val name = etName.text.toString()
                val skill = etSkill.text.toString()
                val location = etLoc.text.toString()
                val priceText = etPrice.text.toString()

                if (name.isNotEmpty() && priceText.isNotEmpty()) {
                    val price = priceText.toInt()
                    repo.addTailor(Tailor(name = name, skill = skill, location = location, price = price))
                    refreshData()
                    Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Nama dan Harga tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun performLogout() {
        session.logout()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}