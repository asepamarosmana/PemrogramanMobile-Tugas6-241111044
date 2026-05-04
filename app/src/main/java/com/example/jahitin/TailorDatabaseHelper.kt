package com.example.jahitin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TailorDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "jahitin.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE tailors (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, skill TEXT, location TEXT, price INTEGER)")
        // Data Awal
        val cv = ContentValues().apply {
            put("name", "Tailor Suryana"); put("skill", "Jas & Kemeja"); put("location", "Cikutra"); put("price", 200000)
        }
        db.insert("tailors", null, cv)
    }
    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {}
}