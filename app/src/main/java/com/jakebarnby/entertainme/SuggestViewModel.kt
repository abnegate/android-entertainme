package com.jakebarnby.entertainme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakebarnby.entertainme.model.ContentType
import com.jakebarnby.entertainme.model.Suggestion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SuggestViewModel : ViewModel() {

    private val _suggestion = MutableStateFlow<Suggestion?>(null)
    val suggestion = _suggestion.asStateFlow()

    private val _suggested = MutableStateFlow(false)
    val suggested = _suggested.asStateFlow()

    private val _thinking = MutableStateFlow(false)
    val thinking = _thinking.asStateFlow()

    fun generateSuggestion() {
        _thinking.value = true

        viewModelScope.launch {
            delay(2000)

            _suggestion.value = Suggestion(
                name = "The Matrix",
                type = ContentType.Movie,
                imageUrl = "https://techcrunch.com/wp-content/uploads/2014/12/matrix.jpg?w=1390&crop=1",
                trail = "Because you watched similar movies in the past 3 months for at least 30 minutes, we think you might like this one."
            )

            _thinking.value = false
            _suggested.value = true
        }
    }

    fun regenerateSuggestion() {
        _suggested.value = false
        _thinking.value = true

        generateSuggestion()
    }

    fun consumeSuggestion() {

    }
}