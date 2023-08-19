package com.example.newsapplicationtest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class RecyclerViewClass(private val activity: Activity, private val mList: ArrayList<JSONObject>) :RecyclerView.Adapter<RecyclerViewClass.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_listitem, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jobj = mList[position]
        holder.textView.text = jobj.getString("title")
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, ViewNewInActivity::class.java)
            intent.putExtra("imageurl", jobj.getString("urlToImage"))
            intent.putExtra("news", jobj.getString("content"))
            activity.startActivity(intent)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.NewTitle)
    }
}