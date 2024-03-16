package com.example.bankapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter1(var this_context: Context, var resources:Int, var items:List<ModelFetchUser>):
    ArrayAdapter<ModelFetchUser>(this_context, resources, items) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater = LayoutInflater.from(this_context)
        val view:View = layoutInflater.inflate(resources, null)

        val text0View = view.findViewById<TextView>(R.id.t0)
        val text1View = view.findViewById<TextView>(R.id.t1)
        val text2View = view.findViewById<TextView>(R.id.t2)
        val text3View = view.findViewById<TextView>(R.id.t3)

        val mItem = items[position]
        text0View.text = mItem.mUserId.toString()
        text1View.text = mItem.mUserName
        text2View.text = mItem.mUserEmail
        text3View.text = mItem.mUserBalance.toString()


        return view
    }

}