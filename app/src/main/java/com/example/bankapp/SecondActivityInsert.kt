package com.example.bankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivityInsert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_insert)

        val etName = findViewById<EditText>(R.id.uName)
        val etEmail = findViewById<EditText>(R.id.uEmail)
        val etBalance = findViewById<EditText>(R.id.uBalance)
        val btnInsert = findViewById<Button>(R.id.uInsert)


        btnInsert.setOnClickListener {
            val user = ModelInsertUser(etName.text.toString(), etEmail.text.toString(), etBalance.text.toString().toInt())
            val db = DBHelper(applicationContext)
            db.insertUserData(user)

            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

    }
}