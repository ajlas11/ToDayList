package com.example.todaylist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskDetailsScreen(taskName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Task Details",
            style = MaterialTheme.typography.headlineSmall // Updated style
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Task Name: $taskName",
            style = MaterialTheme.typography.bodyMedium // Use body style for details
        )
    }
}