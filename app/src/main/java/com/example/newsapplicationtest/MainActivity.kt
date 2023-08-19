package com.example.newsapplicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.newsapplicationtest.CacheStorage.DataStorage
import com.example.newsapplicationtest.api.ApiCallFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        val dataStore = DataStorage(this)

        if (dataStore.articles == "null") {
            CoroutineScope(Dispatchers.IO).launch {
                val apiResult = ApiCallFile().callGetApi("us")
//                textview!!.text = api.toString()
                Log.d("response", "onCreate: " + apiResult)
                dataStore.articles = apiResult
                runOnUiThread {
                    submitData(apiResult)
                }
            }
        } else {
            dataStore.articles?.let { submitData(it) }
        }


    }

    private fun submitData(apiResult: String) {
        val jobj = JSONObject(apiResult)
        val jarry = jobj.getJSONArray("articles")
        val dataArray: ArrayList<JSONObject> = ArrayList()
        for (i in 0 until jarry.length()) {
            dataArray.add(jarry.getJSONObject(i))
        }
        val adapter = RecyclerViewClass(this@MainActivity, dataArray)
        recyclerView!!.adapter = adapter
    }
}