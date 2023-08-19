package com.example.newsapplicationtest.CacheStorage

import android.content.Context

class DataStorage(val context: Context) {
    val pref = context.getSharedPreferences("News_App", 0)
    val editor = pref.edit()

    var articles: String?
        set(value) {
            editor.putString("articles", value)
            editor.commit()
        }
        get() {
            return pref.getString("articles", "null")
        }
}