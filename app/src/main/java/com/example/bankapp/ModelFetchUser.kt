package com.example.bankapp

class ModelFetchUser {
    var mUserName: String = ""
    var mUserEmail: String = ""
    var mUserBalance: Int = 0
    var mUserId: Int = 0

    constructor(u_id: Int, u_name: String, u_email: String, u_balance: Int){
        this.mUserId = u_id
        this.mUserName = u_name
        this.mUserEmail = u_email
        this.mUserBalance = u_balance
    }

    constructor()
}