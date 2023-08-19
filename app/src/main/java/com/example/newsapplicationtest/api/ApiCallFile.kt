package com.example.newsapplicationtest.api

import android.util.Log
import com.example.newsapplicationtest.CacheStorage.DataStorage
import com.example.newsapplicationtest.MainActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import okhttp3.Callback
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ApiCallFile {
    val apiKey = "e85c707fb15544dd9967d288b1cf7944"
    val baseurl = "https://newsapi.org/v2/"
    val gson = GsonBuilder().setLenient().create()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val apiInterface = retrofit.create(ApiInterface::class.java)

    suspend fun callGetApi(country: String): String {
        val call = apiInterface.getNews(country, apiKey)
//        call.enqueue(object : retrofit2.Callback<ResponseBody> {
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.d("OnFailure", "onFailure: " + t)
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                val articles = JSONObject(response.toString()).getJSONArray("articles")
////                val dataStorage = DataStorage(this@ApiCallFile)
//                Log.d("onResponse", "OnResponse: " + articles)
//            }
//        })
        val response: Response<JsonElement> = call.execute()
        Log.d("api", "callGetApi: " + response.code())
        Log.d("api", "callGetApi: " + response.body().toString())
        return response.body().toString()
//        return "Ok"
    }

}