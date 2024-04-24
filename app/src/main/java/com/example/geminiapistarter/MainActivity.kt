package com.example.geminiapistarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.geminiapistarter.components.GeminiButtonType.GeminiButton
import com.example.geminiapistarter.components.GeminiTexFieldType
import com.example.geminiapistarter.ui.theme.MontserratLabelStyle
import com.google.ai.client.generativeai.GenerativeModel
import com.example.geminiapistarter.ui.theme.theme.GeminiAPIStarterTheme
import com.example.geminiapistarter.ui.theme.theme.Gray50

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiAPIStarterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val generativeModel = GenerativeModel(
                        modelName = "gemini-pro",
                        apiKey = BuildConfig.apiKey
                    )
                    val viewModel = SummarizeViewModel(generativeModel)
                    SummarizeRoute(viewModel)
                }
            }
        }
    }
}

@Composable
internal fun SummarizeRoute(
    summarizeViewModel: SummarizeViewModel = viewModel()
) {
    val summarizeUiState by summarizeViewModel.uiState.collectAsState()
    SummarizeScreen(summarizeUiState, onSummarizeClicked = { inputText ->
        summarizeViewModel.summarize(inputText)
    })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SummarizeScreen(
    uiState: SummarizeUiState = SummarizeUiState.Initial,
    onSummarizeClicked: (String) -> Unit = {}
) {
    var prompt by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        bottomBar = {
            Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)) {
                GeminiTexFieldType.GeminiTextField(
                    value = prompt,
                    label = {
                        Text(
                            stringResource(R.string.summarize_label),
                            style = MontserratLabelStyle.Regular.copy(
                                color = Gray50
                            )
                        )
                    },
                    placeholder = { Text(stringResource(R.string.summarize_hint)) },
                    onValueChange = { prompt = it },
                    colors = GeminiTexFieldType.customTextFieldColor(),
                    modifier = Modifier
                        .weight(8f)
                        .align(Alignment.CenterVertically)
                )
                GeminiButton(
                    onClick = {
                        if (prompt.isNotBlank()) {
                            onSummarizeClicked(prompt)
                            keyboardController?.hide()
                            prompt = ""
                        }
                    },

                    modifier = Modifier
                        .weight(2f)
                        .padding(all = 4.dp)
                        .align(Alignment.CenterVertically),
                    text = {
                        Text(
                            text = stringResource(id = R.string.action_go),

                            )
                    }
                )
            }
        }

    ) {
        Column(modifier = Modifier.padding(it)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    when (uiState) {
                        SummarizeUiState.Initial -> {
                            // Nothing is shown
                        }

                        SummarizeUiState.Loading -> {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(all = 8.dp)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is SummarizeUiState.Success -> {
                            Row(modifier = Modifier.padding(all = 8.dp)) {
                                Icon(
                                    Icons.Outlined.Person,
                                    contentDescription = "Person Icon"
                                )
                                Text(
                                    text = uiState.outputText,
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                            }
                        }

                        is SummarizeUiState.Error -> {
                            Text(
                                text = uiState.errorMessage,
                                color = Color.Red,
                                modifier = Modifier.padding(all = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun SummarizeScreenPreview() {
    SummarizeScreen()
}