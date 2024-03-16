package com.example.bankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

class ThirdActivitySelectUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_select_user)

        val mIntent = intent
        val i = mIntent.getIntExtra("key1", 0)
        val pos2: Int = i

        val context = this
        val db = DBHelper(context)
        val data = db.readUserData()

        val listView = findViewById<ListView>(R.id.ll1)

        val list = mutableListOf<ModelFetchUser>()

        for(j in 0 until data.size){
            if(j != i){
                list.add(ModelFetchUser(data[j].mUserId, data[j].mUserName, data[j].mUserEmail, data[j].mUserBalance))
            }
        }

        listView.adapter = MyAdapter2(this, R.layout.activity_third_listview_row, list)

        listView.setOnItemClickListener { _: AdapterView<*>, _: View, position:Int, _:Long ->
            val intent = Intent(applicationContext,FourthActivityEnterSum::class.java)
            intent.putExtra("key2", pos2)
            val pos3:Int = position
            intent.putExtra("key3", pos3)
            startActivity(intent)
        }
    }
}