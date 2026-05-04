package com.example.jahitin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TailorAdapter(private val list: List<Tailor>) : RecyclerView.Adapter<TailorAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.txtName)
        val skill: TextView = v.findViewById(R.id.txtSkill)
        val price: TextView = v.findViewById(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_tailor, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.name
        holder.skill.text = "${item.skill} - ${item.location}"
        holder.price.text = "Mulai Rp ${item.price}"
    }

    override fun getItemCount(): Int = list.size
}