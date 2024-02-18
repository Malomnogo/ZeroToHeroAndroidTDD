package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
            if (max <= 0) throw IllegalStateException("max should be positive, but was $max")
            if (step > max) throw IllegalStateException("max should be more than step")
        }

        override fun initial(number: String) =
            when (number.toInt()) {
                min -> UiState.Min(number)
                max -> UiState.Max(number)
                else -> UiState.Base(number)
            }

        override fun increment(number: String): UiState {
            val currentNumber = number.toInt()
            val increment = currentNumber + step

            return if (step + increment > max) {
                UiState.Max(text = increment.toString())
            } else {
                UiState.Base(text = increment.toString())
            }
        }

        override fun decrement(number: String): UiState {
            val currentNumber = number.toInt()
            val decrement = currentNumber - step

            return if (number.toInt() - step <= min) {
                UiState.Min(decrement.toString())
            } else {
                UiState.Base(text = decrement.toString())
            }
        }
    }
}