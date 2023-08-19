package com.example.newsapplicationtest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplicationtest.api.ApiCallFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewNewInActivity : AppCompatActivity() {
    lateinit var imageUrl: String
    var newsImage: ImageView? = null
    var newsText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_new_in)
        newsImage = findViewById(R.id.newpic)
        newsText = findViewById(R.id.newsToShow)


        imageUrl = intent.getStringExtra("imageurl").toString()
        var image: Bitmap? = null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val `in` = java.net.URL(imageUrl).openStream()
                image = BitmapFactory.decodeStream(`in`)
                runOnUiThread {
                    newsImage!!.setImageBitmap(image)
                }
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                newsImage!!.setImageResource(R.drawable.offline)
                e.printStackTrace()
            }
        }

        newsText!!.text = intent.getStringExtra("news").toString()
        Log.d("news", "onCreate: " + intent.getStringExtra("news").toString() )
    }
}