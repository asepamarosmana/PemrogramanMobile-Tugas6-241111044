package com.example.jahitin

import android.content.ContentValues
import android.content.Context

class TailorRepository(context: Context) {
    private val dbHelper = TailorDatabaseHelper(context)

    fun addTailor(tailor: Tailor) {
        val db = dbHelper.writableDatabase
        val cv = ContentValues().apply {
            put("name", tailor.name); put("skill", tailor.skill)
            put("location", tailor.location); put("price", tailor.price)
        }
        db.insert("tailors", null, cv)
    }

    fun getAllTailors(): List<Tailor> {
        val list = mutableListOf<Tailor>()
        val cursor = dbHelper.readableDatabase.rawQuery("SELECT * FROM tailors", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(Tailor(
                    cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4)
                ))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}