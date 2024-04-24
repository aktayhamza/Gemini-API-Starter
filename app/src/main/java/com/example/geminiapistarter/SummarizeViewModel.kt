package com.example.geminiapistarter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SummarizeViewModel(
    private val generativeModel: GenerativeModel
) : ViewModel() {

    private val _uiStates: MutableStateFlow<List<SummarizeUiState>> =
        MutableStateFlow(emptyList())
    val uiStates: StateFlow<List<SummarizeUiState>> =
        _uiStates.asStateFlow()

    fun summarize(inputText: String) {
        val prompt = "Summarize the following text for me: $inputText"

        viewModelScope.launch {
            try {
                val response = generativeModel.generateContent(prompt)
                response.text?.let { outputContent ->
                    _uiStates.value += SummarizeUiState.Success(outputContent)
                }
            } catch (e: Exception) {
                _uiStates.value += SummarizeUiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}
