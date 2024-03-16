package com.example.bankapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivityProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_profile)


        val mIntent = intent
        val i = mIntent.getIntExtra("key0", 0)
        val pos1:Int = i

        val tv0 = findViewById<TextView>(R.id.tv0)
        val tv1 = findViewById<TextView>(R.id.tv1)
        val tv2 = findViewById<TextView>(R.id.tv2)
        val tv3 = findViewById<TextView>(R.id.tv3)
        val btn1 = findViewById<Button>(R.id.btn1)

        val context = this
        val db = DBHelper(context)
        val data = db.readUserData()

        tv0.text = data[i].mUserId.toString()
        tv1.text = data[i].mUserName
        tv2.text = data[i].mUserEmail
        tv3.text = data[i].mUserBalance.toString()

        btn1.setOnClickListener {
            val intent2 = Intent(applicationContext,ThirdActivitySelectUser::class.java)
            intent2.putExtra("key1", pos1)
            startActivity(intent2)
        }


        val listView = findViewById<ListView>(R.id.lv2)
        val list = mutableListOf<ModelGetSeperatedTransactions>()

        val data1 = db.readTransactionData()

        for(j in 0 until data1.size){
            if(data1[j].senderID == data[i].mUserId)
                list.add(ModelGetSeperatedTransactions(data1[j].receiverName, data1[j].amount,"Debited"))
            if(data1[j].receiverID == data[i].mUserId)
                list.add(ModelGetSeperatedTransactions(data1[j].senderName, data1[j].amount,"Credited"))
        }
        listView.adapter = MyAdapter4(this,R.layout.activity_second_profile_listview_row, list)

    }
}