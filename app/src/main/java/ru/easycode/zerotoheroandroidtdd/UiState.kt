package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun update(button: Button, textView: TextView, progressBar: ProgressBar)

    object ShowProgress : UiState {

        override fun update(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = false
            textView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    data class ShowData(val text: String) : UiState {

        override fun update(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = true
            textView.visibility = View.VISIBLE
            textView.text = text
            progressBar.visibility = View.GONE
        }
    }
}
