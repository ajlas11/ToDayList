package com.example.todaylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todaylist.model.Task // Ensure correct import of the Task data model

@Composable
fun TaskList(
    tasks: List<Task>, // Updated to use your main Task model
    onTaskClick: (Task) -> Unit // Callback when a task is clicked
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(tasks) { task ->
            TaskItem(task = task, onClick = { onTaskClick(task) })
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF3A5FCD)), // Background color of the task card
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Task Name
            Text(
                text = task.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )

            // Priority Indicator (colored dot)
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = task.priorityColor,
                        shape = CircleShape
                    )
                    .padding(end = 16.dp)
            )
        }
    }
}

// Example usage in HomePage or elsewhere
@Composable
fun TaskListPreview() {
    val sampleTasks = listOf(
        Task(id = 1, name = "Groceries", priority = com.example.todaylist.model.Priority.LOW, priorityColor = Color.Green),
        Task(id = 2, name = "Travel plans", priority = com.example.todaylist.model.Priority.MEDIUM, priorityColor = Color.Yellow),
        Task(id = 3, name = "Work", priority = com.example.todaylist.model.Priority.HIGH, priorityColor = Color.Red)
    )
    TaskList(tasks = sampleTasks, onTaskClick = { task ->
        println("Task clicked: ${task.name}")
    })
}
