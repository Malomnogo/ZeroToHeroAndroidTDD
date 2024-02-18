package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun update(textView: TextView, incrementButton: Button, decrementButton: Button)

    abstract class Abstract(
        private val text: String,
        private val incrementEnabled: Boolean = true,
        private val decrementEnabled: Boolean = true
    ) : UiState {

        override fun update(textView: TextView, incrementButton: Button, decrementButton: Button) {
            textView.text = text
            incrementButton.isEnabled = incrementEnabled
            decrementButton.isEnabled = decrementEnabled
        }
    }

    data class Base(private val text: String) : Abstract(text = text)

    data class Max(private val text: String) : Abstract(text = text, incrementEnabled = false)

    data class Min(private val text: String) : Abstract(text = text, decrementEnabled = false)
}