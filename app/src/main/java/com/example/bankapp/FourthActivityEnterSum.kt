package com.example.bankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class FourthActivityEnterSum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth_enter_sum)

        val tv1 = findViewById<TextView>(R.id.tv1)
        val et1 = findViewById<EditText>(R.id.et1)
        val btn1 = findViewById<Button>(R.id.btn1)

        val mIntent = intent

        val idOfSender: Int = mIntent.getIntExtra("key2", 0)
        var idOfReceiver: Int = mIntent.getIntExtra("key3", 0)


        val context = this
        val db = DBHelper(context)
        val data = db.readUserData()

        val name1 = data[idOfSender].mUserName
        val name2 = data[idOfReceiver].mUserName

        if(idOfSender == idOfReceiver || idOfSender < idOfReceiver){
            idOfReceiver += 1
        }

        var temp1 = idOfSender
        var temp2 = idOfReceiver

        val senderName = data[temp1].mUserName
        val receiverName = data[temp2].mUserName

        tv1.text = "You are about to transfer funds from $senderName to $receiverName"

        btn1.setOnClickListener {
            val amountEditText: String = et1.text.toString()
            var amountInteger = 0
            if(amountEditText.isNotEmpty()){
                amountInteger = amountEditText.toInt()
            }

            val availBalance = data[idOfSender].mUserBalance

            if(amountInteger <= availBalance) {
                db.makeATransaction(amountInteger, idOfSender+1, idOfReceiver+1)

                val transfer = ModelInsertTransaction(temp1+1,data[temp1].mUserName, temp2+1, data[temp2].mUserName, amountInteger)
                val db1 = DBHelper(applicationContext)
                db1.insertTransactionData(transfer)

                Toast.makeText(applicationContext,"Successfully transferred",Toast.LENGTH_SHORT).show()
                startActivity(Intent(application,MainActivity::class.java))
            } else {
                Toast.makeText(applicationContext,"Insufficient funds",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
