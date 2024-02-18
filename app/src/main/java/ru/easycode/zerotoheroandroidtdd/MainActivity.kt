package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button
    private lateinit var textView: TextView
    private val count = Count.Base(step = 2, max = 4, min = 0)

    private lateinit var uiState: UiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)
        textView = findViewById(R.id.countTextView)

        incrementButton.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.update(textView, incrementButton, decrementButton)
        }
        decrementButton.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            uiState.update(textView, incrementButton, decrementButton)
        }
        if (savedInstanceState == null) {
            uiState = count.initial(textView.text.toString())
            uiState.update(textView, incrementButton, decrementButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        else
            savedInstanceState.getSerializable(KEY) as UiState
        uiState.update(textView, incrementButton, decrementButton)
    }

    companion object {
        private const val KEY = "savedState"
    }
}