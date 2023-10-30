package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLLITE(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "quanly.db"
        private const val tbl_quanly = "tbl_quanly"
        private const val ID = "id"
        private const val name = "name"
        private const val password = "password"
        private const val email = "email"
        private const val phone = "phone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSql =
            "CREATE TABLE $tbl_quanly ($ID INTEGER PRIMARY KEY, $name TEXT, $password TEXT, $email TEXT, $phone TEXT)"
        db?.execSQL(createTableSql)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $tbl_quanly")
        onCreate(db)
    }

    fun insertQL(std: quanlymodel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(name, std.name)
        contentValues.put(password, std.password)
        contentValues.put(email, std.email)
        contentValues.put(phone, std.phone)
        val success = db.insert(tbl_quanly, null, contentValues)
        db.close()

        if (success != -1L) {
            Log.d("Database", "Data inserted successfully: ID = ${success}")
        } else {
            Log.e("Database", "Failed to insert data into the database")
        }

        return success
    }


    @SuppressLint("Range")
    fun getAllQLL(): ArrayList<quanlymodel> {
        val stdList: ArrayList<quanlymodel> = ArrayList()
        val selectQuery = "SELECT * FROM $tbl_quanly"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            return stdList
        }

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(ID))
                val name = cursor.getString(cursor.getColumnIndex(name))
                val password = cursor.getString(cursor.getColumnIndex(password))
                val email = cursor.getString(cursor.getColumnIndex(email))
                val phone = cursor.getString(cursor.getColumnIndex(phone))
                val std = quanlymodel(id, name, password, email, phone)
                stdList.add(std)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return stdList
    }
    fun deleteQL(id: Int): Int {
        val db = this.writableDatabase
        val whereClause = "$ID = ?"
        val whereArgs = arrayOf(id.toString())
        val rowsDeleted = db.delete(tbl_quanly, whereClause, whereArgs)
        db.close()

        if (rowsDeleted > 0) {
            Log.d("Database", "Data deleted successfully: Rows deleted = $rowsDeleted")
        } else {
            Log.e("Database", "Failed to delete data from the database")
        }

        return rowsDeleted
    }

}
