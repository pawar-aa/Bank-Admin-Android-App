package com.example.bankapp

class ModelGetSeperatedTransactions {
    var personName: String = ""
    var amount: Int = 0
    var transType: String = ""

    constructor(p_n: String, amt: Int, type:String){
        this.personName = p_n
        this.amount = amt
        this.transType = type
    }

    constructor()
}