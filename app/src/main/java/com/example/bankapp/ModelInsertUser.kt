package com.example.bankapp

class ModelInsertUser {
    var usersname: String = ""
    var usersemail: String = ""
    var usersbalance: Int = 0

    constructor(u_name: String, u_email: String, u_balance: Int){
        this.usersname = u_name
        this.usersemail = u_email
        this.usersbalance = u_balance
    }

    constructor()
}