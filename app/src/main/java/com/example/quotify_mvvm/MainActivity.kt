package com.example.quotify_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    lateinit var previous: TextView
    lateinit var next: TextView
    lateinit var button: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())
        previous = findViewById(R.id.onPrevious)
        next = findViewById(R.id.onNext)
        button = findViewById(R.id.shareButton)
        previous.setOnClickListener {
          setQuote(mainViewModel.previousQuote())
        }
        next.setOnClickListener{
            setQuote((mainViewModel.nextQuote()))
        }
        button.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
            startActivity(intent)
        }
    }
fun setQuote(quote: Quote) {
    quoteText.text = quote.text
    quoteAuthor.text = quote.author
}
}