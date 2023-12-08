package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleTextView = findViewById<TextView>(R.id.titleTextView)
        val changeButton = findViewById<Button>(R.id.changeButton)

        changeButton.setOnClickListener {
            savedInstanceState?.putString(TEXT_KEY, "I am an Android Developer!")
            titleTextView.text = "I am an Android Developer!"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_KEY, titleTextView.text.toString())

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        titleTextView.text = savedInstanceState.getString(TEXT_KEY)
    }

}

private const val TEXT_KEY = "textKey"