package com.example.todaylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.todaylist.ui.theme.ToDayListTheme
import com.example.todaylist.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDayListTheme {
                AppNavGraph() // Call your composable function here
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val uiState = viewModel.uiState.collectAsState().value

    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            // Display the UI State
            Text(text = "UI State: $uiState")

            // Button to update state
            Button(onClick = { viewModel.updateState("Updated State!") }) {
                Text(text = "Update State")
            }
        }
    }
}
