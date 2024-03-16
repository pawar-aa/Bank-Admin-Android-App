package com.example.bankapp

class ModelGetTransaction {
    var senderID: Int = 0
    var receiverID: Int = 0
    var amount: Int = 0
    var transID: Int = 0

    constructor(t_id: Int, s_id: Int, r_id: Int, amt: Int){
        this.transID = t_id
        this.senderID = s_id
        this.receiverID = r_id
        this.amount = amt
    }

    constructor()
}