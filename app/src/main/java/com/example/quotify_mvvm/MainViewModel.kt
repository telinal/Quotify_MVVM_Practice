package com.example.quotify_mvvm

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val context: Context): ViewModel() {
    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAsset()
    }

    private fun loadQuoteFromAsset(): Array<Quote> {
val inputStream = context.assets.open("codes.json")
     val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }
    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index]
    fun previousQuote() = quoteList[--index]
}