package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var btnadd: Button
    private lateinit var btnview: Button
    private lateinit var sqllite: SQLLITE
    private lateinit var recyclerView: RecyclerView
    private var adapter: qlAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize view elements
        initView()
        initRecyclerView()
        sqllite = SQLLITE(this)

        btnadd.setOnClickListener { addQL() }
        btnview.setOnClickListener { getQL() }
    }

    private fun getQL() {
        val stdlist = sqllite.getAllQLL()
        Log.e("in ra", "${stdlist.size}")

        // Update the adapter with the data
        adapter?.addItems(stdlist)
    }

    private fun addQL() {
        val name = username.text.toString()
        val password = password.text.toString()
        val email = email.text.toString()
        val phone = phone.text.toString()

        try {
            val std = quanlymodel(name = name, email = email, password = password, phone = phone)
            val status = sqllite.insertQL(std)

            if (status > -1) {
                Toast.makeText(this, "Đã được thêm vào", Toast.LENGTH_SHORT).show()
                clearEditText()
                getQL()
            } else {
                Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearEditText() {
        username.setText("")
        password.setText("")
        email.setText("")
        phone.setText("")
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recView) // Initialize the recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = qlAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        btnadd = findViewById(R.id.btnadd)
        btnview = findViewById(R.id.btnview)
    }
}
