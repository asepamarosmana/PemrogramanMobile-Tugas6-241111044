package com.example.jahitin

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
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

        // Ambil nama dari SharedPreferences
        binding.tvWelcome.text = "Selamat Datang, ${session.getUsername()}!"

        binding.rvTailors.layoutManager = LinearLayoutManager(this)
        refreshData()

        binding.fabAdd.setOnClickListener { showAddDialog() }
    }

    private fun refreshData() {
        val data = repo.getAllTailors()
        binding.rvTailors.adapter = TailorAdapter(data)
    }

    private fun showAddDialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_tailor, null)
        AlertDialog.Builder(this)
            .setTitle("Tambah Tukang Jahit")
            .setView(view)
            .setPositiveButton("Simpan") { _, _ ->
                val name = view.findViewById<EditText>(R.id.etTailorName).text.toString()
                val skill = view.findViewById<EditText>(R.id.etTailorSkill).text.toString()
                val loc = view.findViewById<EditText>(R.id.etTailorLocation).text.toString()
                val price = view.findViewById<EditText>(R.id.etTailorPrice).text.toString().toIntOrNull() ?: 0

                if (name.isNotEmpty()) {
                    repo.addTailor(Tailor(name = name, skill = skill, location = loc, price = price))
                    refreshData() // REFRESH LIST SETELAH SIMPAN
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}