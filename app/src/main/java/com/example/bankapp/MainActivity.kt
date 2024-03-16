package com.example.bankapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNewUser = findViewById<Button>(R.id.btn1)
        btnNewUser.setOnClickListener {
            startActivity(Intent(applicationContext, SecondActivityInsert::class.java))
        }

        val btnTransactions = findViewById<Button>(R.id.btn2)
        btnTransactions.setOnClickListener {
            startActivity(Intent(applicationContext, SecondActivityTransactions::class.java))
        }

        val tv1 = findViewById<TextView>(R.id.tv1)
        tv1.isSelected = true



        val listView = findViewById<ListView>(R.id.listView)

        val list = mutableListOf<ModelFetchUser>()

        val context = this
        val db = DBHelper(context)
        val data = db.readUserData()

        for(i in 0 until data.size){
            list.add(ModelFetchUser(data[i].mUserId, data[i].mUserName, data[i].mUserEmail, data[i].mUserBalance))
        }

        listView.adapter = MyAdapter1(this, R.layout.activity_main_listview_row, list)

        listView.setOnItemClickListener { _: AdapterView<*>, _: View, position: Int, _: Long ->
            val intent1 = Intent(applicationContext, SecondActivityProfile::class.java)
            val pos0:Int = position
            intent1.putExtra("key0", pos0)
            startActivity(intent1)
        }

    }
}