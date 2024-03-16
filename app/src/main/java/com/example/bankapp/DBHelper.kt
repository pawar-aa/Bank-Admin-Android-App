package com.example.bankapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DATABASE_NAME = "BanKDB"
const val TABLE_NAME = "Users"
const val COL_NAME = "Name"
const val COL_EMAIL = "Email"
const val COL_BALANCE = "Balance"
const val COL_ID = "ID"

const val TRANSACTIONS_TABLE_NAME = "Transactions"
const val COL_SENDER_ID = "SenderID"
const val COL_SENDER_NAME = "SenderName"
const val COL_RECEIVER_ID = "ReceiverID"
const val COL_RECEIVER_NAME = "ReceiverName"
const val COL_AMOUNT = "Amount"
const val COL_TRANSACTION_ID = "TransactionID"



class DBHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME VARCHAR(256), $COL_EMAIL VARCHAR(256), $COL_BALANCE INTEGER);"
        db?.execSQL(createTableQuery)
        val createTransactionsTable =
            "CREATE TABLE $TRANSACTIONS_TABLE_NAME($COL_TRANSACTION_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_SENDER_ID INTEGER, $COL_SENDER_NAME VARCHAR(256), $COL_RECEIVER_ID INTEGER, $COL_RECEIVER_NAME VARCHAR(256), $COL_AMOUNT INTEGER);"
        db?.execSQL(createTransactionsTable)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertUserData(user: ModelInsertUser) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, user.usersname)
        cv.put(COL_EMAIL, user.usersemail)
        cv.put(COL_BALANCE, user.usersbalance)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readUserData(): MutableList<ModelFetchUser> {
        val list: MutableList<ModelFetchUser> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                val user = ModelFetchUser()
                user.mUserId = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.mUserName = result.getString(result.getColumnIndex(COL_NAME))
                user.mUserEmail = result.getString(result.getColumnIndex(COL_EMAIL))
                user.mUserBalance = result.getString(result.getColumnIndex(COL_BALANCE)).toInt()
                list.add(user)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun makeATransaction(amt: Int, sender_id: Int, receiver_id: Int) {
        val db = this.writableDatabase
        val query1 =
            "UPDATE $TABLE_NAME SET $COL_BALANCE = $COL_BALANCE+$amt WHERE $COL_ID=$receiver_id;"
        val query2 =
            "UPDATE $TABLE_NAME SET $COL_BALANCE = $COL_BALANCE-$amt WHERE $COL_ID=$sender_id;"

        db.execSQL(query1)
        db.execSQL(query2)
    }

    fun insertTransactionData(transaction: ModelInsertTransaction) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_SENDER_ID, transaction.senderID)
        cv.put(COL_SENDER_NAME,transaction.senderName)
        cv.put(COL_RECEIVER_ID, transaction.receiverID)
        cv.put(COL_RECEIVER_NAME, transaction.receiverName)
        cv.put(COL_AMOUNT, transaction.amount)
        val result = db.insert(TRANSACTIONS_TABLE_NAME, null, cv)
        if (result == (-1).toLong()) {
            Toast.makeText(context, "Failed 1", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readTransactionData(): MutableList<ModelInsertTransaction> {
        val list: MutableList<ModelInsertTransaction> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TRANSACTIONS_TABLE_NAME"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                val trans = ModelInsertTransaction()
                trans.senderID = result.getString(result.getColumnIndex(COL_SENDER_ID)).toInt()
                trans.senderName = result.getString(result.getColumnIndex(COL_SENDER_NAME))
                trans.receiverID = result.getString(result.getColumnIndex(COL_RECEIVER_ID)).toInt()
                trans.receiverName = result.getString(result.getColumnIndex(COL_RECEIVER_NAME))
                trans.amount = result.getString(result.getColumnIndex(COL_AMOUNT)).toInt()
                list.add(trans)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

}