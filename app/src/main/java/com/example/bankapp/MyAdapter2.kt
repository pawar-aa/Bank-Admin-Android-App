package com.example.bankapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter2(var this_context: Context, var resources:Int, var items:List<ModelFetchUser>):
    ArrayAdapter<ModelFetchUser>(this_context, resources, items) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater = LayoutInflater.from(this_context)
        val view:View = layoutInflater.inflate(resources, null)

        val text1View = view.findViewById<TextView>(R.id.tv1)
        val text2View = view.findViewById<TextView>(R.id.tv2)

        val mItem = items[position]
        text1View.text = mItem.mUserName
        text2View.text = mItem.mUserEmail

        return view
    }

}