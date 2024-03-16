package com.example.bankapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class SecondActivityTransactions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_transactions)

        val listView = findViewById<ListView>(R.id.lv1)
        val list = mutableListOf<ModelInsertTransaction>()

        val context = this
        val db = DBHelper(context)
        val data = db.readTransactionData()

        for(j in 0 until data.size){
            list.add(ModelInsertTransaction(data[j].senderID, data[j].senderName, data[j].receiverID, data[j].receiverName, data[j].amount))
        }

        listView.adapter = MyAdapter3(this,R.layout.activity_second_transactions_listview_row, list)

    }
}