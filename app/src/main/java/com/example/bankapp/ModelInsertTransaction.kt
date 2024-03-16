package com.example.bankapp

class ModelInsertTransaction {
    var senderID: Int = 0
    var receiverID: Int = 0
    var amount: Int = 0
    var senderName: String = ""
    var receiverName: String = ""

    constructor(s_id: Int, s_n: String, r_id: Int, r_n:String, amt: Int){
        this.senderID = s_id
        this.senderName = s_n
        this.receiverID = r_id
        this.receiverName = r_n
        this.amount = amt
    }

    constructor()
}